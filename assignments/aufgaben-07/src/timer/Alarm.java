package timer;

import javax.sound.midi.MidiChannel;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;

import static java.util.concurrent.ForkJoinPool.commonPool;

public class Alarm {

    private final MidiChannel channel;

    public Alarm() {
        try {
            var midiSynth = MidiSystem.getSynthesizer();
            midiSynth.open();
            channel = midiSynth.getChannels()[0];
        } catch (MidiUnavailableException e) {
            throw new RuntimeException(e);
        }
    }

    public void sound() {
        commonPool().execute(() -> {
            try {
                for (int i = 0; i < 10; i++) {
                    channel.noteOn(75, 100);
                    Thread.sleep(100);
                }
            } catch (InterruptedException ignored) {}
        });
    }
}
