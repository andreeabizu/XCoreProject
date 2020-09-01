package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCIncludeStatement;
import project.metamodel.entity.XCProject;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IRelationBuilder;
import ro.lrg.xcore.metametamodel.RelationBuilder;

/**
 * 20.12: The time handling functions of library <time.h> shall not be used
 */

@RelationBuilder
public class Rule20_12 implements IRelationBuilder<XCIncludeStatement,XCProject>{
	
	@Override
	public Group<XCIncludeStatement> buildGroup(XCProject arg0) {
		
		Group<XCCompUnit> compU = new Group<>();
		Group<XCIncludeStatement> includeStatement = new Group<>();
		Group<XCIncludeStatement> s = new Group<>();
	
		compU = arg0.compUnitGroup();
		for(XCCompUnit cu: compU.getElements()) 
		{
			s = cu.timeHeaderGroup();
			for(XCIncludeStatement cs:s.getElements())
			{
				includeStatement.add(cs);
			}
		}

		
		return includeStatement;
	}
}


