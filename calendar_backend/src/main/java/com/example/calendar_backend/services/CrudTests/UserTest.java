package com.example.calendar_backend.services.CrudTests;
import com.example.calendar_backend.models.User;
import com.example.calendar_backend.services.UserService;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.sql.Date;

public class UserTest {
    public static void main(String[] args) {
        // Configurare EntityManagerFactory
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager em = emf.createEntityManager();

        // Instanțiere UserService cu EntityManager
        UserService userService = new UserService(em);

        LocalDate birthday = LocalDate.of(2001, 7, 17);
        User newUser = new User(
                null, // va fi setat automat în baza de date
                "madalina_serban000",
                "password123",
                "madalina_serban002@example.com",
                null, // createdAt va fi setat automat de baza de date
                "Madalina",
                "Serban",
                Date.valueOf(birthday)
        );

        // Începe tranzacția
        em.getTransaction().begin();

        // Testează metodele UserService
        try {
            // Verifică dacă username-ul este unic
            if (!userService.isUsernameUnique(newUser.getUsername())) {
                System.out.println("Username is not unique!");
                return;
            }

            // Verifică dacă adresa de email este unică
            if (!userService.isEmailUnique(newUser.getEmail())) {
                System.out.println("Email is not unique!");
                return;
            }

            // Adaugă utilizatorul în baza de date
            userService.createUser(newUser);
            System.out.println("User added successfully with ID: " + newUser.getUserId());

            // Citește utilizatorul din baza de date
            User retrievedUser = userService.getUserByUsername(newUser.getUsername());
            System.out.println("!!!!!!!!!!!!!Retrieved User: " + retrievedUser);

            // Actualizează utilizatorul în baza de date
            retrievedUser.setPassword("newpassword123");
            userService.updateUser(retrievedUser);
            System.out.println("!!!!!!!!!!!!!!User updated successfully!");

            // Citește utilizatorul din nou pentru a verifica actualizarea
            User updatedUser = userService.getUserByUsername(newUser.getUsername());
            System.out.println("!!!!!!!!!!!!!!Updated User: " + updatedUser);

            // Șterge utilizatorul din baza de date
            userService.deleteUserByUsername(newUser.getUsername());
            System.out.println("!!!!!!!!!!!!!!User deleted successfully!");

            // Încearcă citirea utilizatorului șters pentru a verifica ștergerea
            User deletedUser = userService.getUserByUsername(newUser.getUsername());
            if (deletedUser == null) {
                System.out.println("!!!!!!!!!!!!!!User not found, deletion confirmed!");
            } else {
                System.out.println("Error: User still exists!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Commit tranzacția
            em.getTransaction().commit();
            em.close();
            emf.close();
        }
    }
}
