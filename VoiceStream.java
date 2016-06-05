import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class VoiceStream {
    private final static VoiceManager voiceManager = VoiceManager.getInstance();
    private final static Voice voice = voiceManager.getVoice("kevin16");
    private static boolean allocated = false;

    public static void print(String s){
        if (!allocated){
            voice.allocate();
            allocated = true;
        }
        voice.speak(s);
    }
    public static void println(String s){
        if (!allocated){
            voice.allocate();
            allocated = true;
        }
        voice.speak(s);
    }
    public static void printf(String s, Object... objs){
        if (!allocated){
            voice.allocate();
            allocated = true;
        }
        voice.speak(String.format(s, objs));
    }
}
