package projects;


import org.eclipse.cdt.core.model.ICProject;

import project.metamodel.entity.Project;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, Project>{
	
	@Override
	public String compute(Project arg0) {
		ICProject m=(ICProject)arg0.getUnderlyingObject();
		 return m.getElementName();
			 
	}

}