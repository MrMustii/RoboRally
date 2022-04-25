package dtu.roboRally.XGUI;

import java.awt.Image;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.TransferHandler;

public class DragAndDrop extends TransferHandler implements Transferable {
	
	private Image image;
	
	public DragAndDrop() {
		
	}
	
	public int getSourceActions(JComponent c) {
	    return MOVE;
	}

	public Transferable createTransferable(JComponent comp) {
		 // Clear
	    image = null;

	    if (comp instanceof JLabel) {
		    JLabel label = (JLabel) comp;
		    Icon icon = label.getIcon();
		    
		    if (icon instanceof ImageIcon) {
		    	image = ((ImageIcon) icon).getImage();
		        return this;
		    }
	    }
	    return null;
	}

	public void exportDone(JComponent c, Transferable t, int action) {
	    if (action == MOVE) {
//	        c.removeSelection();
	    }
	}
	
	public boolean canImport(TransferHandler.TransferSupport support) {
		return true;
	}
	
	public boolean importData(TransferHandler.TransferSupport support) {
		return true;
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
}
