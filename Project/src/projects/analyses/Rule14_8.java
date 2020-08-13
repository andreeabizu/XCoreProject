package projects.analyses;

import project.metamodel.entity.XCCompUnit;
import project.metamodel.entity.XCProject;
import project.metamodel.entity.XCSourceRoot;
import project.metamodel.entity.XCStatement;
import ro.lrg.xcore.metametamodel.Group;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class Rule14_8 implements IPropertyComputer<String,XCProject>{
	
	@Override
	public String compute(XCProject arg0) {
    String s=new String();
	Group<XCSourceRoot> sourceR= new Group<>();
	Group<XCCompUnit> compU= new Group<>();
	Group<XCStatement> statement = new Group<>();
	
	sourceR=arg0.sourceRootGroup();
	
	for(XCSourceRoot sr:sourceR.getElements()) {
		compU=sr.compUnitGroup();
		for(XCCompUnit cu: compU.getElements()) {
			statement = cu.statementWithoutEnclosedBodyGroup();
			for(XCStatement cs:statement.getElements()) {
				s = s +cs.toString()+ " ";
			}
		}
	}
		
		return s;
	}
}
