import javax.swing.*;
import java.awt.*;

public class Geography_Quiz implements Quiz_Subject {
    private Question[][] questionsByDifficulty;
    
    public Geography_Quiz() {
        questionsByDifficulty = new Question[3][];
        
        // ===== EASY GEOGRAPHY =====
        questionsByDifficulty[0] = new Question[] {
            new Question("What is the capital of France?", 
                new String[]{"Paris", "London", "Berlin", "Rome"}, 0, 1),
            new Question("Which is the largest ocean?", 
                new String[]{"Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"}, 0, 1),
            new Question("What is the longest river in the world?", 
                new String[]{"Nile River", "Amazon River", "Yangtze River", "Mississippi River"}, 0, 1),
            new Question("Which country is known as the Land of the Rising Sun?", 
                new String[]{"Japan", "China", "Thailand", "South Korea"}, 0, 1),
            new Question("What is the capital of Australia?", 
                new String[]{"Canberra", "Sydney", "Melbourne", "Perth"}, 0, 1),
            new Question("Which desert is the largest in the world?", 
                new String[]{"Sahara Desert", "Arabian Desert", "Gobi Desert", "Kalahari Desert"}, 0, 1),
            new Question("What is the capital of Canada?", 
                new String[]{"Ottawa", "Toronto", "Vancouver", "Montreal"}, 0, 1),
            new Question("Which continent is the largest?", 
                new String[]{"Asia", "Africa", "North America", "Antarctica"}, 0, 1),
            new Question("What is the capital of Brazil?", 
                new String[]{"Bras\u00EDlia", "Rio de Janeiro", "S\u00E3o Paulo", "Salvador"}, 0, 1), // í ã
            new Question("Which mountain is the highest in the world?", 
                new String[]{"Mount Everest", "K2", "Kangchenjunga", "Makalu"}, 0, 1),
            new Question("What is the capital of Egypt?", 
                new String[]{"Cairo", "Alexandria", "Giza", "Luxor"}, 0, 1),
            new Question("Which country has the most population?", 
                new String[]{"China", "India", "United States", "Indonesia"}, 0, 1),
            new Question("What is the capital of Russia?", 
                new String[]{"Moscow", "Saint Petersburg", "Novosibirsk", "Yekaterinburg"}, 0, 1),
            new Question("Which is the smallest continent?", 
                new String[]{"Australia", "Europe", "Antarctica", "South America"}, 0, 1),
            new Question("What is the capital of Germany?", 
                new String[]{"Berlin", "Munich", "Hamburg", "Frankfurt"}, 0, 1),
            new Question("Which sea is the largest?", 
                new String[]{"Mediterranean Sea", "Caribbean Sea", "South China Sea", "Bering Sea"}, 0, 1),
            new Question("What is the capital of India?", 
                new String[]{"New Delhi", "Mumbai", "Kolkata", "Bangalore"}, 0, 1),
            new Question("Which country has the most islands?", 
                new String[]{"Sweden", "Norway", "Finland", "Canada"}, 0, 1),
            new Question("What is the capital of South Africa?", 
                new String[]{"Pretoria", "Cape Town", "Johannesburg", "Durban"}, 0, 1),
            new Question("Which is the deepest ocean?", 
                new String[]{"Pacific Ocean", "Atlantic Ocean", "Indian Ocean", "Arctic Ocean"}, 0, 1)
        };
        
        // ===== MEDIUM GEOGRAPHY =====
        questionsByDifficulty[1] = new Question[] {
            new Question("What is the capital of Mongolia?", 
                new String[]{"Ulaanbaatar", "Ulan Bator", "Karakorum", "Erdenet"}, 0, 2),
            new Question("Which country has the most time zones?", 
                new String[]{"France", "Russia", "United States", "United Kingdom"}, 0, 2),
            new Question("What is the capital of Peru?", 
                new String[]{"Lima", "Cusco", "Arequipa", "Trujillo"}, 0, 2),
            new Question("Which river flows through Baghdad?", 
                new String[]{"Tigris River", "Euphrates River", "Nile River", "Jordan River"}, 0, 2),
            new Question("What is the capital of Bhutan?", 
                new String[]{"Thimphu", "Paro", "Punakha", "Wangdue Phodrang"}, 0, 2),
            new Question("Which African country was never colonized?", 
                new String[]{"Ethiopia", "Liberia", "Egypt", "Morocco"}, 0, 2),
            new Question("What is the capital of Kazakhstan?", 
                new String[]{"Nur-Sultan", "Almaty", "Shymkent", "Karaganda"}, 0, 2),
            new Question("Which strait separates Europe from Africa?", 
                new String[]{"Strait of Gibraltar", "Bosporus Strait", "Strait of Hormuz", "Strait of Malacca"}, 0, 2),
            new Question("What is the capital of Venezuela?", 
                new String[]{"Caracas", "Maracaibo", "Valencia", "Barquisimeto"}, 0, 2),
            new Question("Which country has the most volcanoes?", 
                new String[]{"Indonesia", "Japan", "United States", "Chile"}, 0, 2),
            new Question("What is the capital of Uzbekistan?", 
                new String[]{"Tashkent", "Samarkand", "Bukhara", "Khiva"}, 0, 2),
            new Question("Which desert is in Chile?", 
                new String[]{"Atacama Desert", "Patagonian Desert", "Monte Desert", "Sechura Desert"}, 0, 2),
            new Question("What is the capital of Papua New Guinea?", 
                new String[]{"Port Moresby", "Lae", "Madang", "Mount Hagen"}, 0, 2),
            new Question("Which country has the longest coastline?", 
                new String[]{"Canada", "Norway", "Indonesia", "Russia"}, 0, 2),
            new Question("What is the capital of Laos?", 
                new String[]{"Vientiane", "Luang Prabang", "Savannakhet", "Pakse"}, 0, 2),
            new Question("Which sea is completely landlocked?", 
                new String[]{"Caspian Sea", "Aral Sea", "Dead Sea", "Black Sea"}, 0, 2),
            new Question("What is the capital of Turkmenistan?", 
                new String[]{"Ashgabat", "Turkmenabat", "Dashoguz", "Mary"}, 0, 2),
            new Question("Which African river has Victoria Falls?", 
                new String[]{"Zambezi River", "Congo River", "Nile River", "Niger River"}, 0, 2),
            new Question("What is the capital of Madagascar?", 
                new String[]{"Antananarivo", "Toamasina", "Antsirabe", "Mahajanga"}, 0, 2),
            new Question("Which country is both in Europe and Asia?", 
                new String[]{"Russia", "Turkey", "Kazakhstan", "Georgia"}, 0, 2)
        };
        
        // ===== HARD GEOGRAPHY =====
        questionsByDifficulty[2] = new Question[] {
            new Question("What is the capital of Eswatini (formerly Swaziland)?", 
                new String[]{"Mbabane", "Lobamba", "Manzini", "Siteki"}, 0, 3),
            new Question("Which country has the highest number of UNESCO World Heritage Sites?", 
                new String[]{"Italy", "China", "Spain", "Germany"}, 0, 3),
            new Question("What is the capital of Nauru?", 
                new String[]{"Yaren District", "Meneng", "Boe", "Anabar"}, 0, 3),
            new Question("Which river forms the boundary between Texas and Mexico?", 
                new String[]{"Rio Grande", "Colorado River", "Brazos River", "Pecos River"}, 0, 3),
            new Question("What is the capital of Comoros?", 
                new String[]{"Moroni", "Mutsamudu", "Fomboni", "Domoni"}, 0, 3),
            new Question("Which mountain range separates Europe from Asia?", 
                new String[]{"Ural Mountains", "Caucasus Mountains", "Alps", "Carpathian Mountains"}, 0, 3),
            new Question("What is the capital of Kyrgyzstan?", 
                new String[]{"Bishkek", "Osh", "Jalal-Abad", "Karakol"}, 0, 3),
            new Question("Which country has the highest percentage of forest area?", 
                new String[]{"Suriname", "Finland", "Japan", "Brazil"}, 0, 3),
            new Question("What is the capital of Djibouti?", 
                new String[]{"Djibouti City", "Ali Sabieh", "Tadjoura", "Obock"}, 0, 3),
            new Question("Which island is divided between Indonesia and Malaysia?", 
                new String[]{"Borneo", "New Guinea", "Timor", "Sumatra"}, 0, 3),
            new Question("What is the capital of Equatorial Guinea?", 
                new String[]{"Malabo", "Bata", "Ebebiy\u00EDn", "Mongomo"}, 0, 3), // í
            new Question("Which country has the most freshwater lakes?", 
                new String[]{"Canada", "Russia", "United States", "Finland"}, 0, 3),
            new Question("What is the capital of São Tomé and Príncipe?", 
                new String[]{"São Tom\u00E9", "Santo Ant\u00F4nio", "Neves", "Trindade"}, 0, 3), // é ô
            new Question("Which sea has the highest salinity?", 
                new String[]{"Dead Sea", "Red Sea", "Mediterranean Sea", "Caspian Sea"}, 0, 3),
            new Question("What is the capital of Liechtenstein?", 
                new String[]{"Vaduz", "Schaan", "Triesen", "Balzers"}, 0, 3),
            new Question("Which country has the most official languages?", 
                new String[]{"Bolivia", "South Africa", "India", "Switzerland"}, 0, 3),
            new Question("What is the capital of Mauritius?", 
                new String[]{"Port Louis", "Curepipe", "Quatre Bornes", "Beau Bassin-Rose Hill"}, 0, 3),
            new Question("Which African country was named after a British queen?", 
                new String[]{"Lesotho", "Eswatini", "Botswana", "Zimbabwe"}, 0, 3),
            new Question("What is the capital of Brunei?", 
                new String[]{"Bandar Seri Begawan", "Kuala Belait", "Seria", "Tutong"}, 0, 3),
            new Question("Which European country has the most islands?", 
                new String[]{"Sweden", "Norway", "Finland", "Greece"}, 0, 3)
        };
    }
    
    @Override public String getSubjectName() { return "GEOGRAPHY"; }
    @Override
    public Question[] getQuestions(int difficultyLevel) {
        if (difficultyLevel >= 1 && difficultyLevel <= 3) {
            return questionsByDifficulty[difficultyLevel - 1];
        }
        return questionsByDifficulty[1];
    }
    
    @Override public Question[] getQuestions() { return getQuestions(2); }
    
    @Override
    public void startQuiz(JFrame parentFrame, int userId) {
        Quiz_dialoge dialog = new Quiz_dialoge(this, parentFrame, userId, 2);
        dialog.setVisible(true);
    }
}