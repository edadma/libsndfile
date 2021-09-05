//import math._
//
//import io.github.edadma.libsndfile._
//
//object Main extends App {
//
//  val SAMPLE_RATE  = 44100
//  val SAMPLE_COUNT = SAMPLE_RATE * 4 /* 4 seconds */
//  val AMPLITUDE    = 1.0 * 0x7F000000
//  val LEFT_FREQ    = 344.0 / SAMPLE_RATE
//  val RIGHT_FREQ   = 466.0 / SAMPLE_RATE
//
//  val (sndfile, info) = open(
//    "sine.wav",
//    SFM_WRITE,
//    SFInfo(samplerate = SAMPLE_RATE, frames = SAMPLE_COUNT, channels = 2, format = FORMAT_WAV subtype FORMAT_PCM_24))
//
//  if (info.channels > 2) {
//    println("Error : can only generate mono or stereo files.")
//    sndfile.close
//    sys.exit(1)
//  }
//
//  def sample(k: Int): Int =
//    if (info.channels == 1) (AMPLITUDE * sin(LEFT_FREQ * 2 * k * Pi)).toInt
//    else { // stereo
//      if ((k & 1) == 0) // left channel indexes are even
//        (AMPLITUDE * sin(LEFT_FREQ * k * Pi)).toInt // k = 2*i where i is the right channel sample
//      else (AMPLITUDE * sin(RIGHT_FREQ * (k - 1) * Pi)).toInt // k = 2*i + 1 where i is the left channel sample
//    }
//
//  if (sndfile.write_int(sample, info.channels * SAMPLE_COUNT) != info.channels * SAMPLE_COUNT)
//    println(sndfile.strerror)
//
//  sndfile.close
//
//}

import io.github.edadma.libsndfile._

import scala.annotation.tailrec

object Main extends App {

  val (sndfile, info) = open("sine.wav", SFM_READ)

  if (sndfile.isNull) {
    println("Error opening file")
    sndfile.close
    sys.exit(1)
  }

  @tailrec
  def iterate(it: ChunkIterator): Unit =
    if (!it.isNull) {
      val (error, info) = it.get_chunk_size

      if (error == ERR_NO_ERROR) {
        println(info.id)
        iterate(it.next_chunk_iterator)
      }
    }

  iterate(sndfile.get_chunk_iterator(null))
  sndfile.close

}
