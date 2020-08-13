package compUnit;


import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTFunctionDeclarator;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCFunctionDeclarator;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class FunctionsWithNoParamGroup implements IRelationBuilder<XCFunctionDeclarator, XCCompUnit>{
	
	@Override
	public Group<XCFunctionDeclarator> buildGroup(XCCompUnit arg0) {
	IASTTranslationUnit a = null;
	ITranslationUnit m=null;
	ASTVisitor v;
	Group<XCFunctionDeclarator> res = new Group<>();
	try {
	m=(ITranslationUnit)arg0.getUnderlyingObject();
	a= m.getAST();
	}
	catch(CoreException e)
	{
		e.printStackTrace();
	}
    v=new ASTVisitor() {
	
	public int visit(IASTDeclarator c) {

			if(c instanceof  IASTFunctionDeclarator) {
				IASTNode children[] = c.getChildren();
				if(children.length == 1)
				 {
					XCFunctionDeclarator p=Factory.getInstance().createXCFunctionDeclarator(c);
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



