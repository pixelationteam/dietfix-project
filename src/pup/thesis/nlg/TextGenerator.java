package pup.thesis.nlg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.drools.runtime.StatefulKnowledgeSession;

import pup.thesis.knowledgebase.AnswerData;
import pup.thesis.knowledgebase.expert.Experts;
import pup.thesis.logging.App;
import pup.thesis.nlu.pos.PhraseProcessor;
import pup.thesis.nlu.pos.TypedDep;
import pup.thesis.server.DietfixServer;
import pup.thesis.util.ClientData;
import simplenlg.features.Feature;
import simplenlg.features.Tense;
import simplenlg.framework.NLGFactory;
import simplenlg.lexicon.Lexicon;
import simplenlg.phrasespec.SPhraseSpec;
import simplenlg.realiser.english.Realiser;

public class TextGenerator {

	private final Lexicon lexicon;
	private final NLGFactory nlgFactory;
	private final Realiser realiser;

	public TextGenerator() {
		lexicon = Lexicon.getDefaultLexicon();
		nlgFactory = new NLGFactory(lexicon);
		realiser = new Realiser(lexicon);
	}

	public Lexicon getLexicon() {
		return lexicon;
	}

	public NLGFactory getNLGFactory() {
		return nlgFactory;
	}

	public Realiser getRealiser() {
		return realiser;
	}

	public String generateText(ClientData cdata,
			HashMap<Experts, List<AnswerData>> hmap) {

		StatefulKnowledgeSession ksession = DietfixServer.getKBase()
				.getNLGKBase().newStatefulKnowledgeSession();
		App.log("");
		App.log("");
		App.log("");
		App.log("<<<===================== TEXT GENERATION =========================>>>");
		App.log("");
		StringBuilder response = new StringBuilder();
		ArrayList<PhraseProcessor> phrases = new ArrayList<PhraseProcessor>();
		for (List<AnswerData> adata : hmap.values()) {
			int i = 0;
			for (AnswerData answer : adata) {
				App.log("Query Result: " + (++i));
				for (TypedDep tdep : answer.dependencies) {
					App.log(tdep.reln() + "(" + tdep.gov() + "," + tdep.dep()
							+ ")");
				}
				PhraseProcessor asnt = new PhraseProcessor(cdata,
						answer.dependencies, answer.mappings);
				
				phrases.add(asnt);
				//ksession.insert(asnt);
				App.log("");
			}
		}
		
		//   TEXT AGGREGATION HERE
		
		App.log("LEXICALIZATION..");
		App.log(phrases.size());
		for(PhraseProcessor phr: phrases){
			ksession.insert(phr);
		}
		ksession.fireAllRules();
		App.log("");
		App.log("SURFACE REALIZATION..");
		for (Object pproc : ksession.getObjects())
			if (pproc instanceof PhraseProcessor) {
				PhraseProcessor ph = (PhraseProcessor) pproc;
				for (SPhraseSpec specsnt : ph.getSPhrases()) {
					String res = DietfixServer.getTextGenerator().getRealiser()
							.realiseSentence(specsnt);
					response.append(res);
				}
			}

		return response.toString();
	}
}
