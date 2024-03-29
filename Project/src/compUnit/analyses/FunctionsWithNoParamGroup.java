package compUnit.analyses;


import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTParameterDeclaration;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.cdt.internal.core.dom.parser.c.CASTFunctionDeclarator;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCDeclaration;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Rule16_5
 * group of functions with no parameters
 */

@RelationBuilder
public class FunctionsWithNoParamGroup implements IRelationBuilder<XCDeclaration, XCCompUnit>{
	
	@Override
	public Group<XCDeclaration> buildGroup(XCCompUnit arg0) {
	
		IASTTranslationUnit a = null;
		ITranslationUnit m = null;
		ASTVisitor v;
		Group<XCDeclaration> res = new Group<>();
	
		try {
			m = arg0.getUnderlyingObject();
			a = m.getAST();
		}
		catch(CoreException e)
		{
			e.printStackTrace();
		}
		v=new ASTVisitor() {
	
			public int visit(IASTDeclarator c) {

				if(c instanceof  CASTFunctionDeclarator && c.isPartOfTranslationUnitFile()) {
					
					IASTParameterDeclaration[] param =  ((CASTFunctionDeclarator) c).getParameters();
					int role = c.getRoleForName(c.getName());
					
					if(param.length == 0 && (role == IASTFunctionDeclarator.r_definition || role == IASTFunctionDeclarator.r_declaration))
					{  	
						XCDeclaration p = Factory.getInstance().createXCDeclaration(c);
						res.add(p);
						
					}
				}
				
		
				return 3;
			}
	

		};
		v.shouldVisitDeclarators = true;
		a.accept(v);
		return res;

	}
}




