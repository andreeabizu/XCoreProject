package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCExpression;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/*
 * Rule 20.4 (required): Dynamic heap memory allocation shall not be used.
 */
@RelationBuilder
public class Rule20_4 implements IRelationBuilder<XCExpression,XCProject>{

	@Override
	public Group<XCExpression> buildGroup(XCProject arg0) {
  
		Group<XCCompUnit> compU = new Group<>();
		Group<XCExpression> functionCall = new Group<>();
		Group<XCExpression> f = new Group<>();
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			f = cu.functionsForDynamicHeapMemoryAllocationGroup();
			functionCall.addAll(f.getElements());
	      
		}
	
		return functionCall;
	}
}
