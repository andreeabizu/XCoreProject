package compUnit;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTContinueStatement;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTStatement;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCContinueStatement;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

@RelationBuilder
public class ContinueStatementGroup implements IRelationBuilder<XCContinueStatement, XCCompUnit>{
	@Override
  public Group<XCContinueStatement> buildGroup(XCCompUnit arg0) {
	IASTTranslationUnit a = null;
	ITranslationUnit m=null;
	ASTVisitor v;
	Group<XCContinueStatement> res = new Group<>();
	try {
	m=(ITranslationUnit)arg0.getUnderlyingObject();
	a= m.getAST();
	}
	catch(CoreException e)
	{
		e.printStackTrace();
	}
    v=new ASTVisitor() {
	
	public int visit(IASTStatement c) {
		IASTNode statement[] = c.getChildren();
		for(IASTNode r:statement)
		{
			if(r instanceof  IASTContinueStatement) {
				XCContinueStatement p=Factory.getInstance().createXCContinueStatement(r);
				res.add(p);
			}
		}
		return 0;
	}
	

	};
	v.shouldVisitStatements = true;
	a.accept(v);
	return res;
 
}
}
	
	
