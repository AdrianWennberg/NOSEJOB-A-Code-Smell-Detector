import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

/**
 * @version 0.0.1 February 2018 - Cluedo - TeamAbacus
 * @author Alessandro Baccin & William Akinsanya
 *
 * BoardPanel class, models the layout and makeup of the game board.
 */

public class BoardPanel extends JComponent {

    private JPanel boardDisplay, kitchen, ballroom, conservatory, dining_room, billboard, library, hall, study, lounge;
    private JLabel backgroundLabel;
    private ImageIcon backgroundImage;

    public BoardPanel() {
        createUIComponents();
    }

    public JPanel getBoardDisplay() {
        return boardDisplay;
    }

    public JLabel getBackgroundLabel() {
        return backgroundLabel;
    }

    /**
     *
     * @return ArrayList containing room panels.
     */
    public List<JPanel> getListOfPanels() {
        List<JPanel> listOfPanels = new ArrayList<>(Arrays.asList(this.kitchen,
                this.ballroom,
                this.conservatory,
                this.dining_room,
                this.billboard,
                this.library,
                this.study,
                this.hall,
                this.lounge));
        return listOfPanels;
    }

    /**
     * Maps panels to their respective rooms.
     * Loads in board image.
     */
    private void createUIComponents() {
        Color transparentBg = new Color(0, 0, 0, 0);

        this.backgroundLabel = new JLabel();

        this.backgroundImage = new ImageIcon(this.getClass().getResource("board.png"));
        this.backgroundLabel.setIcon(backgroundImage);
        this.boardDisplay = new JPanel();
        this.boardDisplay.setLayout(new FlowLayout());
        this.boardDisplay.add(backgroundLabel);

        this.kitchen = new JPanel();
        this.ballroom = new JPanel();
        this.conservatory = new JPanel();
        this.dining_room = new JPanel();
        this.billboard = new JPanel();
        this.library = new JPanel();
        this.study = new JPanel();
        this.hall = new JPanel();
        this.lounge = new JPanel();

        this.kitchen.setBackground(transparentBg);
        this.ballroom.setBackground(transparentBg);
        this.conservatory.setBackground(transparentBg);
        this.dining_room.setBackground(transparentBg);
        this.billboard.setBackground(transparentBg);
        this.library.setBackground(transparentBg);
        this.study.setBackground(transparentBg);
        this.hall.setBackground(transparentBg);
        this.lounge.setBackground(transparentBg);

        this.kitchen.setSize(135, 135);
        this.ballroom.setSize(175, 130);
        this.conservatory.setSize(135, 90);
        this.dining_room.setSize(185, 130);
        this.billboard.setSize(135, 110);
        this.library.setSize(111, 107);
        this.study.setSize(155, 90);
        this.hall.setSize(130, 155);
        this.lounge.setSize(160, 137);

        this.kitchen.setLocation(41, 45);
        this.ballroom.setLocation(230, 74);
        this.conservatory.setLocation(460, 45);
        this.dining_room.setLocation(38, 257);
        this.billboard.setLocation(458, 210);
        this.library.setLocation(459, 348);
        this.study.setLocation(437, 510);
        this.hall.setLocation(252, 440);
        this.lounge.setLocation(40, 465);

        this.backgroundLabel.add(kitchen);
        this.backgroundLabel.add(ballroom);
        this.backgroundLabel.add(conservatory);
        this.backgroundLabel.add(dining_room);
        this.backgroundLabel.add(billboard);
        this.backgroundLabel.add(library);
        this.backgroundLabel.add(study);
        this.backgroundLabel.add(hall);
        this.backgroundLabel.add(lounge);
    }

}