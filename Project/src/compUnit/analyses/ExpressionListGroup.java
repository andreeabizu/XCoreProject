package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTEnumerationSpecifier;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTExpressionList;
import org.eclipse.cdt.core.dom.ast.IASTForStatement;
import org.eclipse.cdt.core.dom.ast.IASTNode;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCExpressionList;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;


/**
 * Rule12_10
 * expressionList group
 */

@RelationBuilder
public class ExpressionListGroup implements IRelationBuilder<XCExpressionList, XCCompUnit>{
	
	@Override
	public Group<XCExpressionList> buildGroup(XCCompUnit arg0) {
		
		IASTTranslationUnit a = null;
	    ITranslationUnit m = null;
	    Group<XCExpressionList> res = new Group<>();
		try {
			m = arg0.getUnderlyingObject();
			a = m.getAST();
		} 
		catch(CoreException e)
		{
			e.printStackTrace();
		}
		
		ASTVisitor v = new ASTVisitor() {			
			public int visit(IASTExpression c) {
				if(c instanceof IASTExpressionList)
				{   
					int ok = 1;
					IASTNode p1 = c.getParent();
					IASTNode p2 = p1.getParent();
					
					if(p1 instanceof IASTForStatement) {
						if(p1.getChildren()[0] != c && p1.getChildren()[2] != c) {
							ok = 0;
						}
					}
					else
						if(p2 instanceof IASTForStatement) {
							if(p2.getChildren()[0] != p1 && p2.getChildren()[2] != p1) {
								ok = 0;
							}
						}
			    	else 
					 ok = 0;
					if(ok == 0) 
					{
						XCExpressionList expr = Factory.getInstance().createXCExpressionList((IASTExpressionList)c);
						res.add(expr);
					}
				
				}
				
				return PROCESS_CONTINUE;
			}
		};
		
		v.shouldVisitExpressions = true;
		a.accept(v);
		
		return res;
	}
}