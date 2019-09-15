package jp.naist.se.parser;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

public class JavaLexerExample {

	static final String[] FILENAMES = new String[] {
		"example-source/CommentReader.java",
		"example-source/FileAnalyzer.java",
		"example-source/AntlrMultilineCommentReader.java",
		"example-source/FileType.java"
	};
	
	public static void main(String[] args) {
		try {
			for (String f: FILENAMES) {
				// Create a lexer
				CharStream stream = CharStreams.fromFileName(f); 
				Java9Lexer lexer = new Java9Lexer(stream);
				CommonTokenStream tokens = new CommonTokenStream(lexer);

				// Run a lexical analysis
				tokens.fill(); 
				
				// TODO 2. Print token attributes!
				
				// TODO 3. Print "normalized" source code hiding identifier names

			}

		} catch (IOException e) {
		}
	}

}
