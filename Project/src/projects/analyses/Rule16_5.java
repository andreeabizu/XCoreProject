package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCFunction;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * Functions with no parameters shall be declared and defined with the parameter list void.
 */

@RelationBuilder
public class Rule16_5 implements IRelationBuilder<XCFunction,XCProject>{
	
	@Override
	public Group<XCFunction> buildGroup(XCProject arg0) {
	
		Group<XCCompUnit> compU = new Group<>();
		Group<XCFunction> funcD = new Group<>();
		Group<XCFunction> f = new Group<>();
		
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements())
		{
			f = cu.functionsWithNoParamGroup();
			for(XCFunction fd:f.getElements()) 
			{
				funcD.add(fd);
			}
		}
	
		
		return funcD;
	}

}
