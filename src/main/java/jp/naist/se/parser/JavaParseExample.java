package jp.naist.se.parser;

import java.io.IOException;
import java.util.ArrayList;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

import jp.naist.se.parser.Java9Parser.ClassBodyContext;
import jp.naist.se.parser.Java9Parser.CompilationUnitContext;
import jp.naist.se.parser.Java9Parser.ConstructorDeclarationContext;
import jp.naist.se.parser.Java9Parser.EnumBodyContext;
import jp.naist.se.parser.Java9Parser.IfThenElseStatementContext;
import jp.naist.se.parser.Java9Parser.IfThenElseStatementNoShortIfContext;
import jp.naist.se.parser.Java9Parser.IfThenStatementContext;
import jp.naist.se.parser.Java9Parser.InterfaceBodyContext;
import jp.naist.se.parser.Java9Parser.InterfaceMethodDeclarationContext;
import jp.naist.se.parser.Java9Parser.MethodDeclarationContext;
import jp.naist.se.parser.Java9Parser.StaticInitializerContext;


/**
 * メソッドの数を internal class 宣言を無視して数えるところまで実装したもの。
 * @author ishio
 *
 */
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
				
				//System.out.println(Trees.toStringTree(c, parser));

				// TODO 4. Count the number of declared methods in a given file.  
				// TODO 5. Count the number of declared methods ignoring inner classes.
				//MethodCountVisitor v = new MethodCountVisitor();
				MethodCountVisitor v = new OutermostClassMethodCountVisitor();
				c.accept(v);
				System.out.println(f + "," + v.count);
				
				// TODO 6. Print the names of the declared methods.
				MethodNameVisitor v2 = new MethodNameVisitor();
				c.accept(v2);
				for (String m: v2.methodNames) {
					System.out.println(f + ": " + m);
				}

				// TODO 7. Count the number of if statement sequences ("if-else-if-...").
				IfCountVisitor v3 = new IfCountVisitor();
				c.accept(v3);
				System.out.println(f + ",if=" + v3.count);
			}

		} catch (IOException e) {
		}
	}
	
	// For 1
	public static class MethodCountVisitor extends Java9BaseVisitor<Void> {
		
		int count = 0;
		@Override
		public Void visitMethodDeclaration(MethodDeclarationContext ctx) {
			count++;
			return super.visitMethodDeclaration(ctx);
		}
		
		@Override
		public Void visitConstructorDeclaration(ConstructorDeclarationContext ctx) {
			count++;
			return super.visitConstructorDeclaration(ctx);
		}
		
		@Override
		public Void visitInterfaceMethodDeclaration(InterfaceMethodDeclarationContext ctx) {
			count++;
			return super.visitInterfaceMethodDeclaration(ctx);
		}
		
		@Override
		public Void visitStaticInitializer(StaticInitializerContext ctx) {
			count++;
			return super.visitStaticInitializer(ctx);
		}
	}

	// For 2
	public static class OutermostClassMethodCountVisitor extends MethodCountVisitor {
		
		private boolean outermost = false; 
		@Override
		public Void visitClassBody(ClassBodyContext ctx) {
			if (outermost == false) {
				outermost = true;
				super.visitClassBody(ctx);
				outermost = false;
			}
			return null;
		}
		
		@Override
		public Void visitEnumBody(EnumBodyContext ctx) {
			if (outermost == false) {
				outermost = true;
				super.visitEnumBody(ctx);
				outermost = false;
			}
			return null;
		}
		
		@Override
		public Void visitInterfaceBody(InterfaceBodyContext ctx) {
			if (outermost == false) {
				outermost = true;
				super.visitInterfaceBody(ctx);
				outermost = false;
			}
			return null;
		}
		
	}

	// For 3
	public static class MethodNameVisitor extends OutermostClassMethodCountVisitor {
		
		private ArrayList<String> methodNames = new ArrayList<>();
		
		@Override
		public Void visitMethodDeclaration(MethodDeclarationContext ctx) {
			methodNames.add(ctx.methodHeader().methodDeclarator().identifier().getText());
			return super.visitMethodDeclaration(ctx);
		}
		
		@Override
		public Void visitInterfaceMethodDeclaration(InterfaceMethodDeclarationContext ctx) {
			methodNames.add(ctx.methodHeader().methodDeclarator().identifier().getText());
			return super.visitInterfaceMethodDeclaration(ctx);
		}
		
	}
	
	// For 4
	public static class IfCountVisitor extends Java9BaseVisitor<Void> {
		
		int count = 0;
		
		@Override
		public Void visitIfThenStatement(IfThenStatementContext ctx) {
			count++;
			return super.visitIfThenStatement(ctx);
		}
		
		@Override
		public Void visitIfThenElseStatement(IfThenElseStatementContext ctx) {
			count++;
			return super.visitIfThenElseStatement(ctx);
		}
		
		@Override
		public Void visitIfThenElseStatementNoShortIf(IfThenElseStatementNoShortIfContext ctx) {
			count++;
			return super.visitIfThenElseStatementNoShortIf(ctx);
		}
	}

}
