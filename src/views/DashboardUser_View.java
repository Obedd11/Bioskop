package views;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.AbstractTableModel;

import Controllers.DetailTrans_Controller;
import Controllers.Film_Controller;

import Core.Routes;
import Entities.Film_Entity;
import Entities.User_Entity;



public class DashboardUser_View extends JFrame {
    private Film_Controller filmController;
    private FilmTableModel tableModel;
    JTable filmTable;

    public DashboardUser_View(String username,String email) {
        setTitle("Dashboard User");
        setSize(800, 600);
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));

        JLabel lblUsername = new JLabel("Username : " + username);
        add(lblUsername);
        JLabel lblEmail = new JLabel("Email : " + email);
        add(lblEmail);
        filmController = new Film_Controller();
        ArrayList<Film_Entity> films = filmController.read();

        tableModel = new FilmTableModel(films);
        filmTable = new JTable(tableModel);

        JTextField searchField = new JTextField();
        searchField.setColumns(10);
        JButton searchButton = new JButton("Search");

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                updateSearch();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                updateSearch();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
                updateSearch();
            }

            private void updateSearch() {
                String searchText = searchField.getText();
                tableModel.filterByTitle(searchText);
            }
        });

        searchButton.addActionListener(e -> {
            String searchText = searchField.getText();
            tableModel.filterByTitle(searchText);
        });

        JPanel searchPanel = new JPanel();
        searchPanel.add(new JLabel("Search Title: "));
        searchPanel.add(searchField);
        searchPanel.add(searchButton);
        add(searchPanel);
        JButton bookTicketButton = new JButton("Book Ticket");


        JPanel buttonPanel = new JPanel();
        buttonPanel.add(bookTicketButton);
        add(buttonPanel);
        JScrollPane scrollPane = new JScrollPane(filmTable);
        add(scrollPane);

        JTabbedPane tabbedPane = new JTabbedPane();
        add(tabbedPane);

        JButton logoutButton = new JButton("Logout");

        JPanel logoutPanel = new JPanel();
        logoutPanel.add(logoutButton);
        add(logoutPanel);

        try {
            bookTicketButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        bookTicket(username);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            logoutButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        logout();
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            });

            // ... (unchanged)
        } catch (Exception e) {
            e.printStackTrace();
        }

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

    private void bookTicket(String username) {
        try {
            int selectedRow = filmTable.getSelectedRow();
            if (selectedRow != -1) {
                Film_Entity selectedFilm = tableModel.getFilmAt(selectedRow);
                DetailTrans_Controller detailTransaksiController = new DetailTrans_Controller();
                detailTransaksiController.create(new Film_Entity(selectedFilm.getGenre(), selectedFilm.getJudul(), selectedFilm.getJamTayang(), selectedFilm.getStudio(), selectedFilm.isTayang()), new User_Entity(username));
                showBookingConfirmation(selectedFilm);
            } else {
                JOptionPane.showMessageDialog(this, "Please select a film to book a ticket.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showBookingConfirmation(Film_Entity film) {
        try {
            JOptionPane.showMessageDialog(this, "Pesan Tiket film dengan judul : " + film.getJudul());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private class FilmTableModel extends AbstractTableModel {
        private ArrayList<Film_Entity> films = new ArrayList<>();
        private ArrayList<Film_Entity> filteredFilms = new ArrayList<>();
        private String[] columnNames = {"Genre", "Judul", "Jam Tayang", "Studio", "Tayang"};
        private boolean isSearchEmpty = false;

        public FilmTableModel(ArrayList<Film_Entity> films) {
            this.films = films;
            updateFilteredFilms();
        }

        private void updateFilteredFilms() {
            filteredFilms.clear();
            for (Film_Entity film : films) {
                if (film.isTayang()) {
                    filteredFilms.add(film);
                }
            }
            fireTableDataChanged();
        }

        public Film_Entity getFilmAt (int index){
            return filteredFilms.get(index);
        }

        @Override
        public int getRowCount() {
            if (isSearchEmpty) {
                // Display a single row for the message when the search result is empty
                return 1;
            } else {
                return filteredFilms.size();
            }
        }

        @Override
        public int getColumnCount() {
            return columnNames.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            if (isSearchEmpty) {
                return "Maaf judul film yang anda cari tidak ada";
            }

            Film_Entity film = filteredFilms.get(rowIndex);
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

        // Helper method to filter films based on the entered title
        public void filterByTitle(String title) {
            try {
                isSearchEmpty = true;
                filteredFilms.clear();

                for (Film_Entity film : films) {
                    if (film.isTayang() && film.getJudul().toLowerCase().contains(title.toLowerCase())) {
                        filteredFilms.add(film);
                        isSearchEmpty = false;
                    }
                }

                fireTableDataChanged();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    }



