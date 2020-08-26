package function.analyses;

import org.eclipse.cdt.core.dom.ast.IASTImageLocation;
import org.eclipse.cdt.internal.core.dom.parser.c.CASTFunctionDeclarator;
import org.eclipse.core.filebuffers.FileBuffers;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;
import org.eclipse.ui.texteditor.ITextEditor;

import project.metamodel.entity.XCFunction;
import ro.lrg.xcore.metametamodel.ActionPerformer;
import ro.lrg.xcore.metametamodel.HListEmpty;
import ro.lrg.xcore.metametamodel.IActionPerformer;

@ActionPerformer
public class ShowInEditor implements IActionPerformer<Void, XCFunction, HListEmpty>{
	
	@Override
	public Void performAction(XCFunction arg0, HListEmpty arg1) {	
		
		try {			
			CASTFunctionDeclarator c = (CASTFunctionDeclarator)arg0.getUnderlyingObject();
			IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
			IPath path = new Path(c.getContainingFilename());
			IFile file = FileBuffers.getWorkspaceFileAtLocation(path);
			ITextEditor editor = (ITextEditor) IDE.openEditor(page, file);
		    IASTImageLocation l = c.getImageLocation();
	        editor.selectAndReveal(l.getNodeOffset(), c.getLength());
	        
		}
		catch( PartInitException e)
		{	
			e.printStackTrace();	
		
		}
		return null;
	
	}
}