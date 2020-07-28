package demo;

import org.eclipse.cdt.core.model.ICProject;
import org.eclipse.cdt.core.model.IFunction;
import org.eclipse.ui.IStartup;

import ro.lrg.insider.view.ToolRegistration;
import ro.lrg.insider.view.ToolRegistration.XEntityConverter;
import ro.lrg.xcore.metametamodel.XEntity;
import project.metamodel.factory.Factory;

public class Startup1 implements IStartup {

	@Override
	public void earlyStartup() {
		ToolRegistration.getInstance().registerXEntityConverter(
				new XEntityConverter() {
					@Override
					public XEntity convert(Object element) {
						if(element instanceof IFunction)
							return Factory.getInstance().createFunction((IFunction)element);
						else if(element instanceof ICProject)
							return Factory.getInstance().createProject((ICProject)element);
						return null;
						
					}
				});
	}
}