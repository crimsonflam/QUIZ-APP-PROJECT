import javax.swing.*;
import java.awt.*;

public class History_Quiz implements Quiz_Subject {
    private Question[][] questionsByDifficulty;
    
    public History_Quiz() {
        questionsByDifficulty = new Question[3][];
        
        // ===== EASY HISTORY =====
        questionsByDifficulty[0] = new Question[] {
            new Question("Who was the first president of the United States?", 
                new String[]{"George Washington", "Thomas Jefferson", "Abraham Lincoln", "John Adams"}, 0, 1),
            new Question("Which war was fought from 1939 to 1945?", 
                new String[]{"World War II", "World War I", "Korean War", "Vietnam War"}, 0, 1),
            new Question("Who discovered America in 1492?", 
                new String[]{"Christopher Columbus", "Amerigo Vespucci", "Vasco da Gama", "Ferdinand Magellan"}, 0, 1),
            new Question("Ancient Egyptian rulers were called:", 
                new String[]{"Pharaohs", "Emperors", "Kings", "Sultans"}, 0, 1),
            new Question("Which empire built the Colosseum?", 
                new String[]{"Roman Empire", "Greek Empire", "Egyptian Empire", "Persian Empire"}, 0, 1),
            new Question("Who wrote the Declaration of Independence?", 
                new String[]{"Thomas Jefferson", "George Washington", "Benjamin Franklin", "John Adams"}, 0, 1),
            new Question("Which wall divided East and West Berlin?", 
                new String[]{"Berlin Wall", "Iron Curtain", "Great Wall", "Hadrian's Wall"}, 0, 1),
            new Question("Which country had a revolution in 1789?", 
                new String[]{"France", "Russia", "America", "England"}, 0, 1),
            new Question("Who was the first man on the moon?", 
                new String[]{"Neil Armstrong", "Buzz Aldrin", "Yuri Gagarin", "John Glenn"}, 0, 1),
            new Question("Which civilization built Machu Picchu?", 
                new String[]{"Inca", "Aztec", "Maya", "Olmec"}, 0, 1),
            new Question("Which ship sank in 1912?", 
                new String[]{"Titanic", "Lusitania", "Britannic", "Andrea Doria"}, 0, 1),
            new Question("Who was known as the 'Iron Lady'?", 
                new String[]{"Margaret Thatcher", "Indira Gandhi", "Golda Meir", "Queen Elizabeth I"}, 0, 1),
            new Question("Which dynasty built the Great Wall of China?", 
                new String[]{"Qin Dynasty", "Ming Dynasty", "Han Dynasty", "Tang Dynasty"}, 0, 1),
            new Question("Who invented the telephone?", 
                new String[]{"Alexander Graham Bell", "Thomas Edison", "Nikola Tesla", "Guglielmo Marconi"}, 0, 1),
            new Question("Which war had the Battle of Gettysburg?", 
                new String[]{"American Civil War", "Revolutionary War", "World War I", "War of 1812"}, 0, 1),
            new Question("Who was the first emperor of Rome?", 
                new String[]{"Augustus", "Julius Caesar", "Nero", "Constantine"}, 0, 1),
            new Question("Which country was ruled by Queen Victoria?", 
                new String[]{"United Kingdom", "France", "Spain", "Russia"}, 0, 1),
            new Question("Who painted the Mona Lisa?", 
                new String[]{"Leonardo da Vinci", "Michelangelo", "Raphael", "Donatello"}, 0, 1),
            new Question("Which empire was ruled by Genghis Khan?", 
                new String[]{"Mongol Empire", "Ottoman Empire", "Roman Empire", "Persian Empire"}, 0, 1),
            new Question("When did World War I end?", 
                new String[]{"1918", "1914", "1917", "1919"}, 0, 1)
        };
        
        // ===== MEDIUM HISTORY =====
        questionsByDifficulty[1] = new Question[] {
            new Question("Who was the last Tsar of Russia?", 
                new String[]{"Nicholas II", "Alexander III", "Peter the Great", "Catherine the Great"}, 0, 2),
            new Question("Which treaty ended World War I?", 
                new String[]{"Treaty of Versailles", "Treaty of Paris", "Treaty of Vienna", "Treaty of Utrecht"}, 0, 2),
            new Question("Who led the Protestant Reformation?", 
                new String[]{"Martin Luther", "John Calvin", "Henry VIII", "John Wesley"}, 0, 2),
            new Question("Which civilization invented democracy?", 
                new String[]{"Ancient Greece", "Roman Republic", "Ancient India", "Ancient China"}, 0, 2),
            new Question("Who unified Italy in the 19th century?", 
                new String[]{"Giuseppe Garibaldi", "Camillo Cavour", "Victor Emmanuel II", "Benito Mussolini"}, 0, 2),
            new Question("Which empire fell in 1453?", 
                new String[]{"Byzantine Empire", "Roman Empire", "Ottoman Empire", "Holy Roman Empire"}, 0, 2),
            new Question("Who wrote 'The Prince'?", 
                new String[]{"Niccol\u00F2 Machiavelli", "Thomas Hobbes", "John Locke", "Voltaire"}, 0, 2), // ò
            new Question("Which battle marked Napoleon's final defeat?", 
                new String[]{"Waterloo", "Austerlitz", "Trafalgar", "Borodino"}, 0, 2),
            new Question("Who discovered penicillin?", 
                new String[]{"Alexander Fleming", "Louis Pasteur", "Robert Koch", "Joseph Lister"}, 0, 2),
            new Question("Which document limited the power of English kings in 1215?", 
                new String[]{"Magna Carta", "Bill of Rights", "Petition of Right", "Habeas Corpus Act"}, 0, 2),
            new Question("Who was the first female prime minister in the world?", 
                new String[]{"Sirimavo Bandaranaike", "Indira Gandhi", "Margaret Thatcher", "Golda Meir"}, 0, 2),
            new Question("Which war had the Battle of Stalingrad?", 
                new String[]{"World War II", "World War I", "Crimean War", "Russian Civil War"}, 0, 2),
            new Question("Who founded the Mongol Empire?", 
                new String[]{"Genghis Khan", "Kublai Khan", "Attila the Hun", "Tamerlane"}, 0, 2),
            new Question("Which civilization built the pyramids of Giza?", 
                new String[]{"Ancient Egyptians", "Ancient Greeks", "Ancient Romans", "Ancient Persians"}, 0, 2),
            new Question("Who wrote 'The Communist Manifesto'?", 
                new String[]{"Karl Marx and Friedrich Engels", "Vladimir Lenin", "Leon Trotsky", "Joseph Stalin"}, 0, 2),
            new Question("Which country dropped atomic bombs on Hiroshima and Nagasaki?", 
                new String[]{"United States", "Soviet Union", "United Kingdom", "Germany"}, 0, 2),
            new Question("Who was the first emperor of China?", 
                new String[]{"Qin Shi Huang", "Han Wudi", "Tang Taizong", "Kangxi Emperor"}, 0, 2),
            new Question("Which empire was ruled by Suleiman the Magnificent?", 
                new String[]{"Ottoman Empire", "Mughal Empire", "Byzantine Empire", "Safavid Empire"}, 0, 2),
            new Question("Who painted the Sistine Chapel ceiling?", 
                new String[]{"Michelangelo", "Leonardo da Vinci", "Raphael", "Donatello"}, 0, 2),
            new Question("Which revolution began in 1917?", 
                new String[]{"Russian Revolution", "French Revolution", "American Revolution", "Industrial Revolution"}, 0, 2)
        };
        
        // ===== HARD HISTORY =====
        questionsByDifficulty[2] = new Question[] {
            new Question("Which treaty established the European Coal and Steel Community?", 
                new String[]{"Treaty of Paris (1951)", "Treaty of Rome", "Maastricht Treaty", "Treaty of Lisbon"}, 0, 3),
            new Question("Who was the Byzantine general who reconquered North Africa and Italy?", 
                new String[]{"Belisarius", "Narses", "Justinian I", "Heraclius"}, 0, 3),
            new Question("Which ancient civilization developed the concept of zero?", 
                new String[]{"Mayan Civilization", "Indian Civilization", "Babylonian", "Chinese"}, 0, 3),
            new Question("Who was the last ruler of the Incan Empire?", 
                new String[]{"Atahualpa", "Huayna Capac", "Manco Inca", "Pachacuti"}, 0, 3),
            new Question("Which war began with the assassination of Archduke Franz Ferdinand?", 
                new String[]{"World War I", "World War II", "Balkan Wars", "Seven Years' War"}, 0, 3),
            new Question("Who authored 'The Art of War'?", 
                new String[]{"Sun Tzu", "Confucius", "Lao Tzu", "Mencius"}, 0, 3),
            new Question("Which empire was defeated at the Battle of Talas (751 AD)?", 
                new String[]{"Tang Dynasty", "Abbasid Caliphate", "Umayyad Caliphate", "Byzantine Empire"}, 0, 3),
            new Question("Who was the founder of the Maurya Empire?", 
                new String[]{"Chandragupta Maurya", "Ashoka the Great", "Bindusara", "Samudragupta"}, 0, 3),
            new Question("Which Renaissance figure wrote 'Utopia'?", 
                new String[]{"Thomas More", "Erasmus", "Petrarch", "Dante Alighieri"}, 0, 3),
            new Question("Who was the last emperor of the Western Roman Empire?", 
                new String[]{"Romulus Augustulus", "Julius Nepos", "Odoacer", "Justinian I"}, 0, 3),
            new Question("Which conference divided Africa among European powers?", 
                new String[]{"Berlin Conference (1884-85)", "Congress of Vienna", "Yalta Conference", "Paris Peace Conference"}, 0, 3),
            new Question("Who led the Haitian Revolution?", 
                new String[]{"Toussaint Louverture", "Sim\u00F3n Bol\u00EDvar", "Jos\u00E9 de San Mart\u00EDn", "Miguel Hidalgo"}, 0, 3), // ó í é
            new Question("Which Chinese dynasty invented paper?", 
                new String[]{"Han Dynasty", "Qin Dynasty", "Tang Dynasty", "Song Dynasty"}, 0, 3),
            new Question("Who was the Byzantine emperor during the Nika riots?", 
                new String[]{"Justinian I", "Constantine the Great", "Heraclius", "Basil II"}, 0, 3),
            new Question("Which treaty ended the Thirty Years' War?", 
                new String[]{"Peace of Westphalia", "Treaty of Utrecht", "Treaty of Versailles", "Congress of Vienna"}, 0, 3),
            new Question("Who was the first female pharaoh of Egypt?", 
                new String[]{"Hatshepsut", "Cleopatra", "Nefertiti", "Sobekneferu"}, 0, 3),
            new Question("Which civilization built Teotihuacan?", 
                new String[]{"Unknown/Mesoamerican", "Aztec", "Maya", "Olmec"}, 0, 3),
            new Question("Who wrote 'The History of the Peloponnesian War'?", 
                new String[]{"Thucydides", "Herodotus", "Xenophon", "Plutarch"}, 0, 3),
            new Question("Which Mughal emperor built the Taj Mahal?", 
                new String[]{"Shah Jahan", "Akbar", "Jahangir", "Aurangzeb"}, 0, 3),
            new Question("Who led the anti-apartheid movement in South Africa?", 
                new String[]{"Nelson Mandela", "Desmond Tutu", "Steve Biko", "F.W. de Klerk"}, 0, 3)
        };
    }
    
    @Override public String getSubjectName() { return "HISTORY"; }
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