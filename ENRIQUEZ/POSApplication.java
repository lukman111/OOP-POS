import javax.swing.SwingUtilities;


public class POSApplication {
    public static void main(String[] args) {
        
        SwingUtilities.invokeLater(() -> {
            POSjframe frame = new POSjframe();
            frame.setVisible(true);
        });
    }
}