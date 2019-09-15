package jp.naist.se.parser;

import java.io.IOException;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

import jp.naist.se.parser.Java9Parser.CompilationUnitContext;


public class JavaParseExample {

	public static void main(String[] args) {
		try {
			for (String f: JavaLexerExample.FILENAMES) {
				// Create a lexer
				CharStream stream = CharStreams.fromFileName(f); 
				Java9Lexer lexer = new Java9Lexer(stream);
				CommonTokenStream tokens = new CommonTokenStream(lexer);

				// Run a parser
				Java9Parser parser = new Java9Parser(tokens);
				CompilationUnitContext c = parser.compilationUnit();

				// TODO 4. Count the number of declared methods in a given file.  
				MethodCountVisitor v = new MethodCountVisitor();
				c.accept(v);
				//System.out.println(f + "," + v.count);

				// TODO 5. Count the number of declared methods ignoring inner classes.
				
				// TODO 6. Print the names of the declared methods.

				// TODO 7. Count the number of if statement sequences ("if-else-if-...").
			}

		} catch (IOException e) {
		}
	}
	
	public static class MethodCountVisitor extends Java9BaseVisitor<Void> {
		
	}

}
