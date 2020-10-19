package compUnit.analyses;

import org.eclipse.cdt.core.dom.ast.ASTVisitor;
import org.eclipse.cdt.core.dom.ast.IASTExpression;
import org.eclipse.cdt.core.dom.ast.IASTUnaryExpression;
import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.model.ITranslationUnit;
import org.eclipse.core.runtime.CoreException;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCUnaryExpression;
import project.metamodel.factory.Factory;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;


/**
 * Rule12_9
 * Unary minus applied to an unsigned expression
 */

@RelationBuilder
public class UnsignedExpressionWithUnaryMinusGroup implements IRelationBuilder<XCUnaryExpression, XCCompUnit>{
	
	@Override
	public Group<XCUnaryExpression> buildGroup(XCCompUnit arg0) {
		
		IASTTranslationUnit a = null;
	    ITranslationUnit m = null;
	    Group<XCUnaryExpression> res = new Group<>();
	    String s=null;
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
				if(c instanceof IASTUnaryExpression)
				{   int op = ((IASTUnaryExpression) c).getOperator();
			         if((c.getExpressionType()).toString().indexOf("unsigned")!=-1 && op == IASTUnaryExpression.op_minus )
			         {	XCUnaryExpression expr = Factory.getInstance().createXCUnaryExpression((IASTUnaryExpression) c);
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