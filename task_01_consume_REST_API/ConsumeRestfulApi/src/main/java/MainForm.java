import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class MainForm {
    private JPanel panelMain;
    private JTextField company_idTextField;
    private JButton goButton;
    private JList candidateList;
    private JTextField offer_idTextField;
    private JTextField companyName_TextField;
    private JPanel detailpanel;
    private JButton button_download_cv;
    private JLabel label_candidate_id;
    private JList placement_list;
    private JButton btn_ok;

    public MainForm() {
        goButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                candidateList.clearSelection();
                ApiInterface api = new ApiInterface(companyName_TextField.getText(), company_idTextField.getText());
                List<Candidate> candidates = api.retrieveCandidatesByOfferID(offer_idTextField.getText());
                List<String> list_data = new ArrayList<String>();
                for (Candidate candidate : candidates) {
                    list_data.add(candidate.name);
                    System.out.println(candidate.name);
                }
                candidateList.setListData(list_data.toArray());
                candidateList.updateUI();
                candidateList.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent evt) {
                        JList list = (JList) evt.getSource();
                        int index = list.locationToIndex(evt.getPoint());
                        detailpanel.setVisible(true);
                        label_candidate_id.setText(String.valueOf(candidates.get(index).id));
                        button_download_cv.setText("Download CV!");
                        button_download_cv.addMouseListener(new MouseAdapter() {
                            @Override
                            public void mouseClicked(MouseEvent e) {
                                candidates.get(index).downloadCV();
                            }
                        });
                        placement_list.clearSelection();
                        List<String> placement_list_data = new ArrayList<String>();
                        for (Placement placement : candidates.get(index).placements)
                            placement_list_data.add(String.valueOf(placement.id));
                        placement_list.setListData(placement_list_data.toArray());
                        detailpanel.revalidate();
                        detailpanel.repaint();
                    }
                });

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("mainForm");
        frame.setContentPane(new MainForm().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
