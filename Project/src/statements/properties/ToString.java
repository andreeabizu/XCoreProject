package statements.properties;

import org.eclipse.cdt.core.dom.ast.IASTStatement;
import project.metamodel.entity.XCStatement;
import ro.lrg.xcore.metametamodel.IPropertyComputer;
import ro.lrg.xcore.metametamodel.PropertyComputer;

@PropertyComputer
public class ToString implements IPropertyComputer<String, XCStatement>{
	
	@Override
	public String compute(XCStatement arg0) {
		
		IASTStatement c = arg0.getUnderlyingObject();	
		return c.getRawSignature();
		
	}

}
