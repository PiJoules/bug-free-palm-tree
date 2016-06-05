import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Hello_World {

    public static void main(String[] args) {
        
        String voiceName = "kevin16";
        System.out.println(1);
        VoiceManager voiceManager = VoiceManager.getInstance();
        System.out.println(2);
        Voice voice = voiceManager.getVoice(voiceName);
        System.out.println(3);

        voice.allocate();
        System.out.println(4);
        System.out.println(voice.getVolume());
        voice.speak("Hello world!");
        System.out.println(5);
        voice.deallocate();
    }
}
