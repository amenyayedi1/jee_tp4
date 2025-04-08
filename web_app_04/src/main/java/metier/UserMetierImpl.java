package metier;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserMetierImpl implements UserMetierInterface {

    @Override
    public void addUser(User u) {
        try (Connection conn = DBConnexion.getConnection()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement("INSERT INTO user VALUES (0, ?, ?, ?, ?)")) {
                    ps.setString(1, u.getNom());
                    ps.setString(2, u.getPrenom());
                    ps.setString(3, u.getLogin());
                    ps.setString(4, u.getPassword());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> listUsers() {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        try {
            conn = DBConnexion.getConnection();  // Connexion est obtenue avant le bloc try-with-resources.
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM user");
                     ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        User u = new User();
                        u.setId(rs.getInt("id"));
                        u.setNom(rs.getString("nom"));
                        u.setPrenom(rs.getString("prenom"));
                        u.setLogin(rs.getString("login"));
                        u.setPassword(rs.getString("password"));
                        users.add(u);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close(); // Assurez-vous que la connexion est fermée après utilisation
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return users;
    }

    @Override
    public User getUserByLoginAndPassword(String l, String p) {
        User u = null;
        Connection conn = null;
        try {
            conn = DBConnexion.getConnection();  // Connexion obtenue ici
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement("SELECT * FROM user WHERE login = ? AND password = ?")) {
                    ps.setString(1, l);
                    ps.setString(2, p);
                    try (ResultSet rs = ps.executeQuery()) {
                        if (rs != null && rs.next()) {
                            u = new User();
                            u.setId(rs.getInt("id"));
                            u.setNom(rs.getString("nom"));
                            u.setPrenom(rs.getString("prenom"));
                            u.setLogin(rs.getString("login"));
                            u.setPassword(rs.getString("password"));
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                try {
                    conn.close(); // Assurez-vous de fermer la connexion après l'utilisation
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return u;
    }

    @Override
    public void updateUser(User u) {
        try (Connection conn = DBConnexion.getConnection()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement("UPDATE user SET nom = ?, prenom = ?, login = ?, password = ? WHERE id = ?")) {
                    ps.setString(1, u.getNom());
                    ps.setString(2, u.getPrenom());
                    ps.setString(3, u.getLogin());
                    ps.setString(4, u.getPassword());
                    ps.setInt(5, u.getId());
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteUser(int id) {
        try (Connection conn = DBConnexion.getConnection()) {
            if (conn != null) {
                try (PreparedStatement ps = conn.prepareStatement("DELETE FROM user WHERE id = ?")) {
                    ps.setInt(1, id);
                    ps.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public User getUserById(int id) {
        // Récupérer une connexion à la base de données
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User u = null;

        try {
            // Obtenir la connexion
            conn = DBConnexion.getConnection();

            if (conn != null) {
                // Préparer la requête SQL
                String query = "SELECT * FROM User WHERE id = ?";
                ps = conn.prepareStatement(query);
                ps.setInt(1, id);

                // Exécuter la requête
                rs = ps.executeQuery();

                // Traiter le résultat
                if (rs != null && rs.next()) {
                    u = new User();
                    u.setId(rs.getInt("id"));
                    u.setNom(rs.getString("nom"));
                    u.setPrenom(rs.getString("prenom"));
                    u.setLogin(rs.getString("login"));
                    u.setPassword(rs.getString("password"));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Fermer les ressources
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return u;
    }

}
