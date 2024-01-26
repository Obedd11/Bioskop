package views;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import Controllers.DetailTrans_Controller;
import Controllers.Film_Controller;
import Core.Routes;
import Entities.DetailTrans_Entity;
import Entities.Film_Entity;

public class DashboardAdmin_View extends JFrame {

    private Film_Controller filmController;
    private DetailTrans_Controller detailTransaksiController;
    private FilmTableModel tableModel;
    private DetailTransaksiTableModel tableModel2;
    private JTable filmTable;
    private JTable detailTransaksiTable;

    private JTextField genreField;
    private JTextField judulField;
    private JTextField jamTayangField;
    private JTextField studioField;
    private JCheckBox tayangCheckbox;
    public DashboardAdmin_View(String username, String email) {
        setTitle("Dashboard Admin");
        setSize(800, 600);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel lblUsername = new JLabel("Username: " + username);
        add(lblUsername);

        filmController = new Film_Controller();
        ArrayList<Film_Entity> films = filmController.read();
        detailTransaksiController = new DetailTrans_Controller();
        ArrayList<DetailTrans_Entity> detailTransaksiEntities = detailTransaksiController.read();
        tableModel2 = new DetailTransaksiTableModel(detailTransaksiEntities);
        tableModel = new FilmTableModel(films);
        filmTable = new JTable(tableModel);
        detailTransaksiTable = new JTable(tableModel2);
        JScrollPane scrollPane2 = new JScrollPane(detailTransaksiTable);
        add(scrollPane2);
        JScrollPane scrollPane = new JScrollPane(filmTable);
        add(scrollPane);

        genreField = new JTextField();
        judulField = new JTextField();
        jamTayangField = new JTextField();
        studioField = new JTextField();
        tayangCheckbox = new JCheckBox("Tayang");

        JButton btnAdd = new JButton("Add Film");
        JButton btnEdit = new JButton("Edit Film");
        JButton btnDelete = new JButton("Delete Film");

        filmTable.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = filmTable.getSelectedRow();
            if (selectedRow != -1) {
                // Get the selected film
                Film_Entity selectedFilm = tableModel.getFilmAt(selectedRow);

                // Update input fields with selected film data
                genreField.setText(selectedFilm.getGenre());
                judulField.setText(selectedFilm.getJudul());
                jamTayangField.setText(selectedFilm.getJamTayang());
                studioField.setText(selectedFilm.getStudio());
                tayangCheckbox.setSelected(selectedFilm.isTayang());
            }
        });
        btnAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addFilm();
            }
        });

        btnEdit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                editFilm();
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteFilm();
            }
        });

        JButton logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });

        JPanel logoutPanel = new JPanel();
        logoutPanel.add(logoutButton);
        add(logoutPanel);

        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new BoxLayout(inputPanel, BoxLayout.Y_AXIS));
        inputPanel.add(new JLabel("Genre: "));
        inputPanel.add(genreField);
        inputPanel.add(new JLabel("Judul: "));
        inputPanel.add(judulField);
        inputPanel.add(new JLabel("Jam Tayang: "));
        inputPanel.add(jamTayangField);
        inputPanel.add(new JLabel("Studio: "));
        inputPanel.add(studioField);
        inputPanel.add(tayangCheckbox);
        add(inputPanel);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);
        add(buttonPanel);

        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    private void logout() {
        try {
            Routes.logout_controller.logut();
            setVisible(false);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    private void addFilm() {
        try {
            String genre = genreField.getText();
            String judul = judulField.getText();
            String jamTayang = jamTayangField.getText();
            String studio = studioField.getText();
            boolean tayang = tayangCheckbox.isSelected();
            filmController.create(genre, judul, jamTayang, studio, tayang);
            tableModel.updateFilmList();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void editFilm() {
        try {
            int selectedRow = filmTable.getSelectedRow();
            if (selectedRow != -1) {
                String genre = genreField.getText();
                String judul = judulField.getText();
                String jamTayang = jamTayangField.getText();
                String studio = studioField.getText();
                boolean tayang = tayangCheckbox.isSelected();

                filmController.update(selectedRow, genre, judul, jamTayang, studio, tayang);
                tableModel.fireTableDataChanged();

                clearInputFields();
                tableModel.updateFilmList();
            } else {
                JOptionPane.showMessageDialog(this, "Please select a film to edit.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void deleteFilm() {
        int selectedRow = filmTable.getSelectedRow();
        if (selectedRow != -1) {
            filmController.delete(selectedRow);
            tableModel.updateFilmList();
            clearInputFields();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a film to delete.");
        }
    }

    private void clearInputFields() {
        genreField.setText("");
        judulField.setText("");
        jamTayangField.setText("");
        studioField.setText("");
        tayangCheckbox.setSelected(false);
    }

    private class FilmTableModel extends AbstractTableModel {
        private ArrayList<Film_Entity> films;
        private String[] columnNames = {"Genre", "Judul", "Jam Tayang", "Studio", "Tayang"};

        public FilmTableModel(ArrayList<Film_Entity> films) {
            this.films = films;
        }

        @Override
        public int getRowCount() {
            return films.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        public Film_Entity getFilmAt(int index) {
            return films.get(index);
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Film_Entity film = films.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return film.getGenre();
                case 1:
                    return film.getJudul();
                case 2:
                    return film.getJamTayang();
                case 3:
                    return film.getStudio();
                case 4:
                    return film.isTayang();
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }

        private void updateFilmList() {
            tableModel = new FilmTableModel(filmController.read());
            filmTable.setModel(tableModel);
        }
    }

    private class DetailTransaksiTableModel extends AbstractTableModel {
        private ArrayList<DetailTrans_Entity> detailTransaksis;


        private String[] columnNames = {"Username", "Judul", "Jam Tayang", "Studio", "Tayang"};

        public DetailTransaksiTableModel(ArrayList<DetailTrans_Entity> detailTransaksis) {
            this.detailTransaksis = detailTransaksis;
        }

        @Override
        public int getRowCount() {
            return detailTransaksis.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        public DetailTrans_Entity getFilmAt(int index) {
            return detailTransaksis.get(index);
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            DetailTrans_Entity film = detailTransaksis.get(rowIndex);
            switch (columnIndex) {
                case 0:
                    return film.getUserEntity().getUsername();
                case 1:
                    return film.getFilmEntity().getJudul();
                case 2:
                    return film.getFilmEntity().getJamTayang();
                case 3:
                    return film.getFilmEntity().getStudio();
                case 4:
                    return film.getFilmEntity().isTayang();
                default:
                    return null;
            }
        }

        @Override
        public String getColumnName(int column) {
            return columnNames[column];
        }
    }
}
