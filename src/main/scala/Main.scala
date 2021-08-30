import math._

import io.github.edadma.libsndfile.facade._

object Main extends App {

  val SAMPLE_RATE  = 44100
  val SAMPLE_COUNT = SAMPLE_RATE * 4 /* 4 seconds */
  val AMPLITUDE    = 1.0 * 0x7F000000
  val LEFT_FREQ    = 344.0 / SAMPLE_RATE
  val RIGHT_FREQ   = 466.0 / SAMPLE_RATE

  val (sndfile, sfinfo) = sf_open("sine.wav",
                                  SFM_WRITE,
                                  SFInfo(samplerate = SAMPLE_RATE,
                                         frames = SAMPLE_COUNT,
                                         channels = 2,
                                         format = SF_FORMAT_WAV subtype SF_FORMAT_PCM_24))

}
