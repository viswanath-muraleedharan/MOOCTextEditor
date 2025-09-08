package textgen;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 
 * An implementation of the MTG interface that uses a list of lists.
 * @author UC San Diego Intermediate Programming MOOC team 
 */
public class MarkovTextGeneratorLoL implements MarkovTextGenerator {

	// The list of words with their next words
	private List<ListNode> wordList; 
	
	// The starting "word"
	private String starter;
	
	// The previous "word"
	private String prevWord;
	
	// The current "word"
	private String word;
	
	// The random number generator
	private Random rnGenerator;

	Map<String, Integer> wordIndex = new HashMap<>();

	public MarkovTextGeneratorLoL(Random generator)
	{
		wordList = new LinkedList<ListNode>();
		starter = "";
		rnGenerator = generator;
	}

	/** Train the generator by adding the sourceText */
	@Override
	public void train(String sourceText)
	{
		ArrayList<String> words = getWords("[^ ]+", sourceText);
		if(!words.isEmpty()) {
			int index = 0;
			starter = words.get(0);
			prevWord = starter;

			for(int i=1; i<words.size(); i++) {
				word = words.get(i);
				if(wordIndex.containsKey(prevWord)) {
					ListNode node = wordList.get(wordIndex.get(prevWord));
					node.addNextWord(word);
				} else {
					wordIndex.put(prevWord, index++);
					ListNode node = new ListNode(prevWord);
					node.addNextWord(word);
					wordList.add(node);
				}
				prevWord = word;
			}

			ListNode node;
			if(wordIndex.containsKey(prevWord)) {
				node = wordList.get(wordIndex.get(prevWord));
			} else {
				wordIndex.put(prevWord, index++);
				node = new ListNode(prevWord);
				wordList.add(node);
			}
			node.addNextWord(starter);
		}
	}

	/** 
	 * Generate the number of words requested.
	 */
	@Override
	public String generateText(int numWords)
	{
		if(wordList.isEmpty()) {
			return "";
		}

		String currWord = starter;
		StringBuilder output = new StringBuilder();
		if (numWords > 0)
		{
			output.append(currWord);
		}

		int i = 1;
		String word;
		ListNode node ;

		while(i < numWords) {
			node = wordList.get(wordIndex.get(currWord));
			word = node.getRandomNextWord(rnGenerator);
			output.append(" ");
			output.append(word);
			currWord = word;
			i++;
		}
		return output.toString();
	}


	// Can be helpful for debugging
	@Override
	public String toString()
	{
		String toReturn = "";
		for (ListNode n : wordList)
		{
			toReturn += n.toString();
		}
		return toReturn;
	}
	
	/** Retrain the generator from scratch on the source text */
	@Override
	public void retrain(String sourceText)
	{
		wordList.clear();
		train(sourceText);
	}


	private ArrayList<String> getWords(String pattern, String sourceText)
	{
		ArrayList<String> tokens = new ArrayList<String>();
		Pattern tokSplitter = Pattern.compile(pattern);
		Matcher m = tokSplitter.matcher(sourceText);
		
		while (m.find()) {
			tokens.add(m.group());
		}
		return tokens;
	}
	
	
	/**
	 * This is a minimal set of tests.  Note that it can be difficult
	 * to test methods/classes with randomized behavior.   
	 * @param args
	 */
	public static void main(String[] args)
	{
		// feed the generator a fixed random value for repeatable behavior
		MarkovTextGeneratorLoL gen = new MarkovTextGeneratorLoL(new Random(42));
		String textString = "Hello.  Hello there.  This is a test.  Hello there.  Hello Bob.  Test again.";
		System.out.println(textString);
		gen.train(textString);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
		System.out.println();
		String textString2 = "You say yes, I say no, "+
				"You say stop, and I say go, go, go, "+
				"Oh no. You say goodbye and I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"I say high, you say low, "+
				"You say why, and I say I don't know. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"Why, why, why, why, why, why, "+
				"Do you say goodbye. "+
				"Oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello. "+
				"You say yes, I say no, "+
				"You say stop and I say go, go, go. "+
				"Oh, oh no. "+
				"You say goodbye and I say hello, hello, hello. "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello, "+
				"I don't know why you say goodbye, I say hello, hello, hello,";
		System.out.println(textString2);
		gen.retrain(textString2);
		System.out.println(gen);
		System.out.println(gen.generateText(20));
	}

}

/** Links a word to the next words in the list 
 * You should use this class in your implementation. */
class ListNode
{
    // The word that is linking to the next words
	private String word;
	
	// The next words that could follow it
	private List<String> nextWords;

	ListNode(String word)
	{
		this.word = word;
		nextWords = new LinkedList<String>();
	}

	public String getWord()
	{
		return word;
	}

	public void addNextWord(String nextWord)
	{
		nextWords.add(nextWord);
	}

	public String getRandomNextWord(Random generator)
	{
	    // The random number generator should be passed from 
	    // the MarkovTextGeneratorLoL class
		return nextWords.get(generator.nextInt(nextWords.size()));
	}

	public String toString()
	{
		String toReturn = word + ": ";
		for (String s : nextWords) {
			toReturn += s + "->";
		}
		toReturn += "\n";
		return toReturn;
	}

}

