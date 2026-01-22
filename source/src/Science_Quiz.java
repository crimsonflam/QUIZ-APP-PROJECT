import javax.swing.*;
import java.awt.*;

public class Science_Quiz implements Quiz_Subject {
    private Question[][] questionsByDifficulty;
    
    public Science_Quiz() {
        questionsByDifficulty = new Question[3][];
        
        // ===== EASY SCIENCE =====
        questionsByDifficulty[0] = new Question[] {
            new Question("What planet is known as the Red Planet?", 
                new String[]{"Mars", "Venus", "Jupiter", "Saturn"}, 0, 1),
            new Question("What is H\u2082O?",  // H₂O
                new String[]{"Water", "Hydrogen", "Oxygen", "Salt"}, 0, 1),
            new Question("What gas do humans breathe in?", 
                new String[]{"Oxygen", "Carbon Dioxide", "Nitrogen", "Hydrogen"}, 0, 1),
            new Question("How many bones in adult human body?", 
                new String[]{"206", "200", "196", "210"}, 0, 1),
            new Question("Which is NOT a state of matter?", 
                new String[]{"Fire", "Solid", "Liquid", "Gas"}, 0, 1),
            new Question("What is Earth's primary source of energy?", 
                new String[]{"Sun", "Moon", "Wind", "Core"}, 0, 1),
            new Question("Which organ pumps blood?", 
                new String[]{"Heart", "Lung", "Liver", "Brain"}, 0, 1),
            new Question("What is the boiling point of water (\u00B0C)?",  // °C
                new String[]{"100", "0", "50", "212"}, 0, 1),
            new Question("Photosynthesis produces:", 
                new String[]{"Oxygen", "Carbon Dioxide", "Nitrogen", "Water"}, 0, 1),
            new Question("Largest planet in solar system?", 
                new String[]{"Jupiter", "Saturn", "Earth", "Mars"}, 0, 1),
            new Question("What is the hardest natural substance?", 
                new String[]{"Diamond", "Gold", "Iron", "Quartz"}, 0, 1),
            new Question("Humans are:", 
                new String[]{"Mammals", "Reptiles", "Amphibians", "Fish"}, 0, 1),
            new Question("Number of planets in solar system?", 
                new String[]{"8", "9", "7", "10"}, 0, 1),
            new Question("Main component of air?", 
                new String[]{"Nitrogen", "Oxygen", "Carbon Dioxide", "Hydrogen"}, 0, 1),
            new Question("What does DNA stand for?", 
                new String[]{"Deoxyribonucleic Acid", "Deoxy Nucleic Acid", "Dinucleic Acid", "Deoxyribo Acid"}, 0, 1),
            new Question("Speed of sound in air (m/s)?", 
                new String[]{"343", "299", "1500", "500"}, 0, 1),
            new Question("Chemical symbol for gold?", 
                new String[]{"Au", "Ag", "Go", "Gd"}, 0, 1),
            new Question("Nearest star to Earth?", 
                new String[]{"Sun", "Alpha Centauri", "Polaris", "Sirius"}, 0, 1),
            new Question("What is rust?", 
                new String[]{"Iron Oxide", "Copper Carbonate", "Aluminum Oxide", "Zinc Sulfate"}, 0, 1),
            new Question("Force that pulls objects down?", 
                new String[]{"Gravity", "Magnetism", "Friction", "Inertia"}, 0, 1)
        };
        
        // ===== MEDIUM SCIENCE =====
        questionsByDifficulty[1] = new Question[] {
            new Question("What is the pH of pure water?", 
                new String[]{"7", "0", "14", "1"}, 0, 2),
            new Question("What is Newton's First Law?", 
                new String[]{"Law of Inertia", "F=ma", "Action-Reaction", "Law of Gravity"}, 0, 2),
            new Question("Chemical symbol for sodium?", 
                new String[]{"Na", "So", "Sd", "Sm"}, 0, 2),
            new Question("What does RNA stand for?", 
                new String[]{"Ribonucleic Acid", "Ribo Nucleic Acid", "Ribose Acid", "Ribosomal Acid"}, 0, 2),
            new Question("Unit of electric current?", 
                new String[]{"Ampere", "Volt", "Ohm", "Watt"}, 0, 2),
            new Question("Which is a noble gas?", 
                new String[]{"Helium", "Oxygen", "Nitrogen", "Hydrogen"}, 0, 2),
            new Question("What is acceleration due to gravity (m/s\u00B2)?",  // ²
                new String[]{"9.8", "10", "9.5", "9.2"}, 0, 2),
            new Question("Blood type O is:", 
                new String[]{"Universal Donor", "Universal Recipient", "Rarest Type", "Most Common Only in Asia"}, 0, 2),
            new Question("Process by which plants make food?", 
                new String[]{"Photosynthesis", "Respiration", "Transpiration", "Fermentation"}, 0, 2),
            new Question("What is a catalyst?", 
                new String[]{"Speeds up reaction", "Slows down reaction", "Stops reaction", "Changes products"}, 0, 2),
            new Question("DNA bases are:", 
                new String[]{"A,T,G,C", "A,U,G,C", "X,Y,Z,W", "1,2,3,4"}, 0, 2),
            new Question("Light year measures:", 
                new String[]{"Distance", "Time", "Speed", "Brightness"}, 0, 2),
            new Question("What is Ohm's Law?", 
                new String[]{"V=IR", "P=IV", "F=ma", "E=mc\u00B2"}, 0, 2), // ²
            new Question("Mitosis results in:", 
                new String[]{"2 identical cells", "4 different cells", "1 cell with double DNA", "Gametes"}, 0, 2),
            new Question("What is specific heat of water (J/g\u00B0C)?",  // °C
                new String[]{"4.18", "1.0", "2.1", "0.5"}, 0, 2),
            new Question("Most abundant element in universe?", 
                new String[]{"Hydrogen", "Helium", "Oxygen", "Carbon"}, 0, 2),
            new Question("Endothermic reaction:", 
                new String[]{"Absorbs heat", "Releases heat", "No heat change", "Creates light"}, 0, 2),
            new Question("Red blood cells contain:", 
                new String[]{"Hemoglobin", "Chlorophyll", "Melanin", "Insulin"}, 0, 2),
            new Question("SI unit of force?", 
                new String[]{"Newton", "Joule", "Pascal", "Watt"}, 0, 2),
            new Question("What is a covalent bond?", 
                new String[]{"Shares electrons", "Transfers electrons", "Metal bonds", "Ionic attraction"}, 0, 2)
        };
        
        // ===== HARD SCIENCE =====
        questionsByDifficulty[2] = new Question[] {
            new Question("Heisenberg Uncertainty Principle states:", 
                new String[]{"Position/momentum can't both be known", "Energy is quantized", "Wave-particle duality", "Speed of light constant"}, 0, 3),
            new Question("Schr\u00F6dinger's cat illustrates:",  // ö
                new String[]{"Quantum superposition", "Relativity", "Entropy", "Nuclear decay"}, 0, 3),
            new Question("Standard Model includes:", 
                new String[]{"Quarks and Leptons", "Only Fermions", "Strings and Branes", "Ether and Phlogiston"}, 0, 3),
            new Question("Planck's constant value (J\u22C5s)?",  // ⋅
                new String[]{"6.626\u00D710\u207B\u00B3\u2074", "3.00\u00D710\u2078", "1.602\u00D710\u207B\u00B9\u2079", "9.109\u00D710\u207B\u00B3\u00B9"}, 0, 3), 
            new Question("CRISPR-Cas9 is used for:", 
                new String[]{"Gene editing", "Protein synthesis", "DNA sequencing", "Cell division"}, 0, 3),
            new Question("Mitochondrial DNA is inherited from:", 
                new String[]{"Mother only", "Father only", "Both parents", "Randomly"}, 0, 3),
            new Question("What is the Higgs boson?", 
                new String[]{"Gives particles mass", "Carries weak force", "Dark matter particle", "Quantum gravity particle"}, 0, 3),
            new Question("Quantum entanglement allows:", 
                new String[]{"Instant correlation at distance", "Faster-than-light travel", "Time travel", "Parallel universes"}, 0, 3),
            new Question("PCR amplifies:", 
                new String[]{"DNA", "RNA", "Proteins", "Cells"}, 0, 3),
            new Question("General Relativity explains:", 
                new String[]{"Gravity as curvature", "Quantum mechanics", "Electromagnetism", "Strong nuclear force"}, 0, 3),
            new Question("Standard temperature/pressure (STP) is:", 
                new String[]{"0\u00B0C, 1 atm", "25\u00B0C, 1 atm", "0\u00B0C, 760 torr", "273K, 101.3 kPa"}, 0, 3), // °C
            new Question("Pauli Exclusion Principle applies to:", 
                new String[]{"Fermions", "Bosons", "All particles", "Only electrons"}, 0, 3),
            new Question("RNA polymerase function:", 
                new String[]{"Transcribes DNA to RNA", "Translates RNA to protein", "Replicates DNA", "Degrades RNA"}, 0, 3),
            new Question("Special Relativity says:", 
                new String[]{"Speed of light constant for all observers", "Time is absolute", "Mass increases with velocity", "Space is Euclidean"}, 0, 3),
            new Question("What is dark energy?", 
                new String[]{"Causes universe expansion acceleration", "Binds galaxies", "Makes up stars", "Source of black holes"}, 0, 3),
            new Question("Krebs cycle occurs in:", 
                new String[]{"Mitochondria", "Cytoplasm", "Nucleus", "Ribosomes"}, 0, 3),
            new Question("Second Law of Thermodynamics:", 
                new String[]{"Entropy increases", "Energy conserved", "Heat flows hot to cold", "Absolute zero unattainable"}, 0, 3),
            new Question("PCR annealing temperature depends on:", 
                new String[]{"Primer Tm", "DNA length", "GC content", "All of above"}, 0, 3),
            new Question("Standard Model force carriers:", 
                new String[]{"Gluon, Photon, W/Z, Higgs", "Quarks only", "Leptons only", "Graviton"}, 0, 3),
            new Question("What is epigenetics?", 
                new String[]{"Gene expression changes without DNA change", "Gene mutations", "Chromosome number changes", "Protein folding"}, 0, 3)
        };
    }
    
    @Override public String getSubjectName() { return "SCIENCE"; }
    
    @Override
    public Question[] getQuestions(int difficultyLevel) {
        if (difficultyLevel >= 1 && difficultyLevel <= 3) {
            return questionsByDifficulty[difficultyLevel - 1];
        }
        return questionsByDifficulty[1];
    }
    
    @Override
    public Question[] getQuestions() { return getQuestions(2); }
    
    @Override
    public void startQuiz(JFrame parentFrame, int userId) {
        Quiz_dialoge dialog = new Quiz_dialoge(this, parentFrame, userId, 2);
        dialog.setVisible(true);
    }
}