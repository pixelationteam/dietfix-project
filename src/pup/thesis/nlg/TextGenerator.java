package pup.thesis.nlg;

import java.util.List;

import org.drools.runtime.StatefulKnowledgeSession;

import pup.thesis.knowledgebase.AnswerData;
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

	public String generateText(ClientData cdata, List<AnswerData> adata) {
		StringBuilder response = new StringBuilder();
		StatefulKnowledgeSession ksession = DietfixServer.getKBase()
				.getNLGKBase().newStatefulKnowledgeSession();
		for (AnswerData answer : adata) {
			String s = "";
			for (TypedDep tdep : answer.dependencies) {
				s += tdep.reln() + "(" + tdep.gov() + "," + tdep.dep() + ")";
				s += " X ";
			}
			App.log("TextGen:", s);
			PhraseProcessor asnt = new PhraseProcessor(cdata,
					answer.dependencies, answer.mappings);
			ksession.insert(asnt);
		}
		ksession.fireAllRules();
		for (Object pproc : ksession.getObjects())
			if (pproc instanceof PhraseProcessor) {
				PhraseProcessor ph = (PhraseProcessor) pproc;
				for (SPhraseSpec specsnt : ph.getSPhrases()) {
					App.log(DietfixServer.getTextGenerator().getRealiser()
							.realiseSentence(specsnt));
				}
			}
		return response.toString();
	}
}
