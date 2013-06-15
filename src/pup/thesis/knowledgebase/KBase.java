package pup.thesis.knowledgebase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.drools.KnowledgeBase;
import org.drools.KnowledgeBaseFactory;
import org.drools.builder.KnowledgeBuilder;
import org.drools.builder.KnowledgeBuilderError;
import org.drools.builder.KnowledgeBuilderErrors;
import org.drools.builder.KnowledgeBuilderFactory;
import org.drools.builder.ResourceType;
import org.drools.io.ResourceFactory;

import pup.thesis.helper.MysqlHelper;
import pup.thesis.knowledgebase.expert.Dietitian;
import pup.thesis.knowledgebase.expert.Expert;
import pup.thesis.knowledgebase.expert.ExpertAnswer;
import pup.thesis.knowledgebase.expert.Experts;
import pup.thesis.knowledgebase.expert.FitnessInstructor;
import pup.thesis.knowledgebase.expert.Nutritionist;
import pup.thesis.logging.App;
import pup.thesis.nlu.CoreParser;
import pup.thesis.nlu.pos.TypedDep;
import pup.thesis.nlu.pos.Word;
import pup.thesis.server.DietfixServer;
import pup.thesis.util.ClientData;

import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TypedDependency;

public class KBase {

	private final KnowledgeBase FOLKbase;
	private final KnowledgeBase NLGKbase;
	private final static KnowledgeBuilder kbuilder1 = KnowledgeBuilderFactory
			.newKnowledgeBuilder();
	private final static KnowledgeBuilder kbuilder2 = KnowledgeBuilderFactory
			.newKnowledgeBuilder();
	private final QueryFilter qfilter;

	public KBase() {
		FOLKbase = createKBase("FOL.drl", kbuilder1);
		NLGKbase = createKBase("NLG.drl", kbuilder2);
		qfilter = new QueryFilter();
	}

	public KnowledgeBase getFOLKBase() {
		return FOLKbase;
	}

	public KnowledgeBase getNLGKBase() {
		return NLGKbase;
	}

	private KnowledgeBase createKBase(String drl, KnowledgeBuilder kbuilder) {
		kbuilder.add(ResourceFactory.newClassPathResource(drl),
				ResourceType.DRL);
		KnowledgeBuilderErrors errors = kbuilder.getErrors();
		if (errors.size() > 0) {
			for (KnowledgeBuilderError error : errors) {
				System.err.println(error);
			}
			throw new IllegalArgumentException("Could not parse knowledge.");
		}
		KnowledgeBase kb = KnowledgeBaseFactory.newKnowledgeBase();
		kb.addKnowledgePackages(kbuilder.getKnowledgePackages());
		return kb;
	}

	public HashMap<Experts, List<AnswerData>> process(ClientData cdata,List<TypedDep> tdep)
			throws Exception {

		App.log("");
		App.log("");
		App.log("");
		App.log("<<<===================== INFORMATION RETRIEVAL =========================>>>");
		App.log("");
		HashMap<Experts,List<AnswerData>> hret = new HashMap<Experts,List<AnswerData>>();
		HashMap<Experts, List<String>> hmap = qfilter.filter(tdep);
		App.log("");
	

		for (Experts exp : hmap.keySet()) {
			Expert expert;
			switch(exp){
			case DIETITIAN:
				expert = new Dietitian(cdata);
				break;
			case FITNESS_INSTRUCTOR:
				expert = new FitnessInstructor(cdata);
				break;
			case NUTRITIONIST:
				expert = new Nutritionist(cdata);
				break;
			default:
				expert = null;
			}
			List<String> keywords = hmap.get(exp);
			if(keywords.isEmpty()){
				continue;
			}
			List<ExpertAnswer> lexp = expert.getAnswers(keywords);
			ArrayList<AnswerData> adata = new ArrayList<AnswerData>();

			App.log("");
			App.log("Query Result", lexp.size());
			App.log("-------------------------------------------------------");
			int i = 0;
			for (ExpertAnswer tds : lexp) {
				App.log("Result["+i+"] ID",tds.getId());
				App.log("Result["+i+"] Data",tds.getDesc());
				AnswerData ad = tds.getAnswerData();
				adata.add(ad);
				i++;
				App.log("");
			}
			App.log("");
			hret.put(exp, adata);
		}

		// MOFA here..

		return hret;

	}

	public void insertAnswer(ClientData cdata, Experts x, String sentence,
			KeyTagSet keytags, HashMap<Integer, String> hm) {

		Connection conn;

		CoreParser parser;
		if (!DietfixServer.isRunning()) {

			DietfixServer.start();
		}
		parser = DietfixServer.getParser();
		Tree pt = parser.getParseTree(sentence);

		List<CoreLabel> wlabel = pt.taggedLabeledYield();
		Word[] words = new Word[wlabel.size()];
		for (int i = 0; i < words.length; i++) {
			words[i] = new Word(wlabel.get(i).index(), wlabel.get(i).tag(),
					wlabel.get(i).word());
		}
		List<TypedDependency> tdep = parser.getDependencies(pt);
		TypedDep[] tda = new TypedDep[tdep.size()];
		for (int i = 0; i < tdep.size(); i++) {
			tda[i] = new TypedDep(tdep.get(i));
			App.log(tda[i].toString());
		}

		AnswerData ad = new AnswerData(tda, hm);

		/*
		 * StatefulKnowledgeSession ksession =
		 * DietfixServer.getKBase().getNLGKBase().newStatefulKnowledgeSession();
		 * PhraseProcessor asnt = new PhraseProcessor(cdata,tda,hm);
		 * ksession.insert(asnt); ksession.fireAllRules(); for(SPhraseSpec
		 * specsnt : asnt.getSPhrases()){
		 * App.log(DietfixServer.getTextGenerator(
		 * ).getRealiser().realiseSentence(specsnt)); }
		 */
		String query = "insert into dietfix_ans(AnsObj,AnsExpert,AnsDesc) values (?,?,?)";

		try {
			conn = MysqlHelper.createDietfixConnection(cdata);
			PreparedStatement statement = conn.prepareStatement(query);
			statement.setObject(1, (Object) ad);
			statement.setString(2, x.name());
			statement.setString(3, keytags.toString());
			statement.execute();
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		App.log("Done");
	}

	public static AnswerData createAnswerData(Tree parsetree,
			HashMap<Integer, String> map) {
		List<CoreLabel> wlabel = parsetree.taggedLabeledYield();
		for (CoreLabel lbl : wlabel) {
			if (map.containsKey(lbl.index())) {
				App.log(lbl.tag());
			}
		}
		return null;
	}

}
