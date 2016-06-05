import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class VoiceStream {
    private static VoiceManager voiceManager = VoiceManager.getInstance();
    private static Voice voice = voiceManager.getVoice("kevin16");

    public static void print(String s){
        voice.speak(s);
    }
    public static void println(String s){
        voice.speak(s);
    }
    public static void printf(String s, Object... objs){
        voice.speak(String.format(s, objs));
    }
}
