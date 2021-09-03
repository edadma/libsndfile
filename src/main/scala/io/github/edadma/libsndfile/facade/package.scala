package io.github.edadma.libsndfile

import io.github.edadma.libsndfile.extern.{LibSndfile => sf}

import scala.collection.mutable
import scala.scalanative.unsafe._
import scala.scalanative.unsigned._

package object facade {

  implicit class FormatType(val value: CInt) extends AnyVal {
    def subtype(subtype: FormatSubtype): FormatType = FormatType(value | subtype.value)
    def endian(endian: Endian): FormatType          = FormatType(value | endian.value)
  }

  lazy val SF_FORMAT_WAV: FormatType   = FormatType(0x10000)
  lazy val SF_FORMAT_AIFF: FormatType  = FormatType(0x20000)
  lazy val SF_FORMAT_AU: FormatType    = FormatType(0x30000)
  lazy val SF_FORMAT_RAW: FormatType   = FormatType(0x40000)
  lazy val SF_FORMAT_PAF: FormatType   = FormatType(0x50000)
  lazy val SF_FORMAT_SVX: FormatType   = FormatType(0x60000)
  lazy val SF_FORMAT_NIST: FormatType  = FormatType(0x70000)
  lazy val SF_FORMAT_VOC: FormatType   = FormatType(0x80000)
  lazy val SF_FORMAT_IRCAM: FormatType = FormatType(0xa0000)
  lazy val SF_FORMAT_W64: FormatType   = FormatType(0xb0000)
  lazy val SF_FORMAT_MAT4: FormatType  = FormatType(0xc0000)
  lazy val SF_FORMAT_MAT5: FormatType  = FormatType(0xd0000)
  lazy val SF_FORMAT_PVF: FormatType   = FormatType(0xe0000)
  lazy val SF_FORMAT_XI: FormatType    = FormatType(0xf0000)
  lazy val SF_FORMAT_HTK: FormatType   = FormatType(0x100000)
  lazy val SF_FORMAT_SDS: FormatType   = FormatType(0x110000)
  lazy val SF_FORMAT_AVR: FormatType   = FormatType(0x120000)
  lazy val SF_FORMAT_WAVEX: FormatType = FormatType(0x130000)
  lazy val SF_FORMAT_SD2: FormatType   = FormatType(0x160000)
  lazy val SF_FORMAT_FLAC: FormatType  = FormatType(0x170000)
  lazy val SF_FORMAT_CAF: FormatType   = FormatType(0x180000)
  lazy val SF_FORMAT_WVE: FormatType   = FormatType(0x190000)
  lazy val SF_FORMAT_OGG: FormatType   = FormatType(0x200000)
  lazy val SF_FORMAT_MPC2K: FormatType = FormatType(0x210000)
  lazy val SF_FORMAT_RF64: FormatType  = FormatType(0x220000)

  implicit class FormatSubtype(val value: CInt) extends AnyVal

  lazy val SF_FORMAT_PCM_S8: FormatSubtype       = FormatSubtype(0x1)
  lazy val SF_FORMAT_PCM_16: FormatSubtype       = FormatSubtype(0x2)
  lazy val SF_FORMAT_PCM_24: FormatSubtype       = FormatSubtype(0x3)
  lazy val SF_FORMAT_PCM_32: FormatSubtype       = FormatSubtype(0x4)
  lazy val SF_FORMAT_PCM_U8: FormatSubtype       = FormatSubtype(0x5)
  lazy val SF_FORMAT_FLOAT: FormatSubtype        = FormatSubtype(0x6)
  lazy val SF_FORMAT_DOUBLE: FormatSubtype       = FormatSubtype(0x7)
  lazy val SF_FORMAT_ULAW: FormatSubtype         = FormatSubtype(0x10)
  lazy val SF_FORMAT_ALAW: FormatSubtype         = FormatSubtype(0x11)
  lazy val SF_FORMAT_IMA_ADPCM: FormatSubtype    = FormatSubtype(0x12)
  lazy val SF_FORMAT_MS_ADPCM: FormatSubtype     = FormatSubtype(0x13)
  lazy val SF_FORMAT_GSM610: FormatSubtype       = FormatSubtype(0x20)
  lazy val SF_FORMAT_VOX_ADPCM: FormatSubtype    = FormatSubtype(0x21)
  lazy val SF_FORMAT_NMS_ADPCM_16: FormatSubtype = FormatSubtype(0x22)
  lazy val SF_FORMAT_NMS_ADPCM_24: FormatSubtype = FormatSubtype(0x23)
  lazy val SF_FORMAT_NMS_ADPCM_32: FormatSubtype = FormatSubtype(0x24)
  lazy val SF_FORMAT_G721_32: FormatSubtype      = FormatSubtype(0x30)
  lazy val SF_FORMAT_G723_24: FormatSubtype      = FormatSubtype(0x31)
  lazy val SF_FORMAT_G723_40: FormatSubtype      = FormatSubtype(0x32)
  lazy val SF_FORMAT_DWVW_12: FormatSubtype      = FormatSubtype(0x40)
  lazy val SF_FORMAT_DWVW_16: FormatSubtype      = FormatSubtype(0x41)
  lazy val SF_FORMAT_DWVW_24: FormatSubtype      = FormatSubtype(0x42)
  lazy val SF_FORMAT_DWVW_N: FormatSubtype       = FormatSubtype(0x43)
  lazy val SF_FORMAT_DPCM_8: FormatSubtype       = FormatSubtype(0x50)
  lazy val SF_FORMAT_DPCM_16: FormatSubtype      = FormatSubtype(0x51)
  lazy val SF_FORMAT_VORBIS: FormatSubtype       = FormatSubtype(0x60)
  lazy val SF_FORMAT_OPUS: FormatSubtype         = FormatSubtype(0x64)
  lazy val SF_FORMAT_ALAC_16: FormatSubtype      = FormatSubtype(0x70)
  lazy val SF_FORMAT_ALAC_20: FormatSubtype      = FormatSubtype(0x71)
  lazy val SF_FORMAT_ALAC_24: FormatSubtype      = FormatSubtype(0x72)
  lazy val SF_FORMAT_ALAC_32: FormatSubtype      = FormatSubtype(0x73)

  implicit class Endian(val value: CInt) extends AnyVal

  lazy val SF_ENDIAN_FILE: Endian   = Endian(0x0)
  lazy val SF_ENDIAN_LITTLE: Endian = Endian(0x10000000)
  lazy val SF_ENDIAN_BIG: Endian    = Endian(0x20000000)
  lazy val SF_ENDIAN_CPU: Endian    = Endian(0x30000000)

  implicit class Mode(val value: CInt) extends AnyVal

  lazy val SFM_READ: Mode  = Mode(0x10)
  lazy val SFM_WRITE: Mode = Mode(0x20)
  lazy val SFM_RDWR: Mode  = Mode(0x30)

  //  lazy val SF_FORMAT_SUBMASK  = 0xffff
//  lazy val SF_FORMAT_TYPEMASK = 0xfff0000)
//  lazy val SF_FORMAT_ENDMASK  = 0x30000000)

  implicit class Whence(val value: CInt) extends AnyVal

  lazy val SEEK_SET: Whence = Whence(0) /* Seek from beginning of file.  */
  lazy val SEEK_CUR: Whence = Whence(1) /* Seek from current position.  */
  lazy val SEEK_END: Whence = Whence(2) /* Seek from end of file.  */

  implicit class Command(val value: CInt) extends AnyVal

  lazy val SFC_GET_LIB_VERSION: Command            = Command(0x1000)
  lazy val SFC_GET_LOG_INFO: Command               = Command(0x1001)
  lazy val SFC_GET_CURRENT_SF_INFO: Command        = Command(0x1002) //
  lazy val SFC_GET_NORM_DOUBLE: Command            = Command(0x1010)
  lazy val SFC_GET_NORM_FLOAT: Command             = Command(0x1011)
  lazy val SFC_SET_NORM_DOUBLE: Command            = Command(0x1012)
  lazy val SFC_SET_NORM_FLOAT: Command             = Command(0x1013)
  lazy val SFC_SET_SCALE_FLOAT_INT_READ: Command   = Command(0x1014)
  lazy val SFC_SET_SCALE_INT_FLOAT_WRITE: Command  = Command(0x1015)
  lazy val SFC_GET_SIMPLE_FORMAT_COUNT: Command    = Command(0x1020)
  lazy val SFC_GET_SIMPLE_FORMAT: Command          = Command(0x1021)
  lazy val SFC_GET_FORMAT_INFO: Command            = Command(0x1028)
  lazy val SFC_GET_FORMAT_MAJOR_COUNT: Command     = Command(0x1030)
  lazy val SFC_GET_FORMAT_MAJOR: Command           = Command(0x1031)
  lazy val SFC_GET_FORMAT_SUBTYPE_COUNT: Command   = Command(0x1032)
  lazy val SFC_GET_FORMAT_SUBTYPE: Command         = Command(0x1033)
  lazy val SFC_CALC_SIGNAL_MAX: Command            = Command(0x1040)
  lazy val SFC_CALC_NORM_SIGNAL_MAX: Command       = Command(0x1041)
  lazy val SFC_CALC_MAX_ALL_CHANNELS: Command      = Command(0x1042)
  lazy val SFC_CALC_NORM_MAX_ALL_CHANNELS: Command = Command(0x1043)
  lazy val SFC_GET_SIGNAL_MAX: Command             = Command(0x1044)
  lazy val SFC_GET_MAX_ALL_CHANNELS: Command       = Command(0x1045)
  lazy val SFC_SET_ADD_PEAK_CHUNK: Command         = Command(0x1050)
  lazy val SFC_UPDATE_HEADER_NOW: Command          = Command(0x1060)
  lazy val SFC_SET_UPDATE_HEADER_AUTO: Command     = Command(0x1061)
  lazy val SFC_FILE_TRUNCATE: Command              = Command(0x1080)
  lazy val SFC_SET_RAW_START_OFFSET: Command       = Command(0x1090)
  lazy val SFC_SET_DITHER_ON_WRITE: Command        = Command(0x10a0)
  lazy val SFC_SET_DITHER_ON_READ: Command         = Command(0x10a1)
  lazy val SFC_GET_DITHER_INFO_COUNT: Command      = Command(0x10a2)
  lazy val SFC_GET_DITHER_INFO: Command            = Command(0x10a3)
  lazy val SFC_GET_EMBED_FILE_INFO: Command        = Command(0x10b0)
  lazy val SFC_SET_CLIPPING: Command               = Command(0x10c0)
  lazy val SFC_GET_CLIPPING: Command               = Command(0x10c1)
  lazy val SFC_GET_CUE_COUNT: Command              = Command(0x10cd)
  lazy val SFC_GET_CUE: Command                    = Command(0x10ce)
  lazy val SFC_SET_CUE: Command                    = Command(0x10cf)
  lazy val SFC_GET_INSTRUMENT: Command             = Command(0x10d0)
  lazy val SFC_SET_INSTRUMENT: Command             = Command(0x10d1)
  lazy val SFC_GET_LOOP_INFO: Command              = Command(0x10e0)
  lazy val SFC_GET_BROADCAST_INFO: Command         = Command(0x10f0)
  lazy val SFC_SET_BROADCAST_INFO: Command         = Command(0x10f1)
  lazy val SFC_GET_CHANNEL_MAP_INFO: Command       = Command(0x1100)
  lazy val SFC_SET_CHANNEL_MAP_INFO: Command       = Command(0x1101)
  lazy val SFC_RAW_DATA_NEEDS_ENDSWAP: Command     = Command(0x1110)
  lazy val SFC_WAVEX_SET_AMBISONIC: Command        = Command(0x1200)
  lazy val SFC_WAVEX_GET_AMBISONIC: Command        = Command(0x1201)
  lazy val SFC_RF64_AUTO_DOWNGRADE: Command        = Command(0x1210)
  lazy val SFC_SET_VBR_ENCODING_QUALITY: Command   = Command(0x1300)
  lazy val SFC_SET_COMPRESSION_LEVEL: Command      = Command(0x1301)
  lazy val SFC_SET_OGG_PAGE_LATENCY_MS: Command    = Command(0x1302)
  lazy val SFC_SET_OGG_PAGE_LATENCY: Command       = Command(0x1303)
  lazy val SFC_SET_CART_INFO: Command              = Command(0x1400)
  lazy val SFC_GET_CART_INFO: Command              = Command(0x1401)
  lazy val SFC_SET_ORIGINAL_SAMPLERATE: Command    = Command(0x1500)
  lazy val SFC_GET_ORIGINAL_SAMPLERATE: Command    = Command(0x1501)
  lazy val SFC_TEST_IEEE_FLOAT_REPLACE: Command    = Command(0x6001)
  lazy val SFC_SET_ADD_HEADER_PAD_CHUNK: Command   = Command(0x1051)
  lazy val SFC_SET_ADD_DITHER_ON_WRITE: Command    = Command(0x1070)
  lazy val SFC_SET_ADD_DITHER_ON_READ: Command     = Command(0x1071)

  object Field {
    def iterator: Iterator[Field] =
      new Iterator[Field] {
        private var cur = SF_STR_FIRST.value - 1

        def hasNext: Boolean = cur < SF_STR_LAST.value

        def next: Field =
          if (hasNext) {
            cur += 1
            cur
          } else
            throw new NoSuchElementException("no more string fields")
      }
  }

  implicit class Field(val value: CInt) extends AnyVal

  lazy val SF_STR_FIRST: Field = SF_STR_TITLE
  lazy val SF_STR_TITLE        = new Field(0x1)
  lazy val SF_STR_COPYRIGHT    = new Field(0x2)
  lazy val SF_STR_SOFTWARE     = new Field(0x3)
  lazy val SF_STR_ARTIST       = new Field(0x4)
  lazy val SF_STR_COMMENT      = new Field(0x5)
  lazy val SF_STR_DATE         = new Field(0x6)
  lazy val SF_STR_ALBUM        = new Field(0x7)
  lazy val SF_STR_LICENSE      = new Field(0x8)
  lazy val SF_STR_TRACKNUMBER  = new Field(0x9)
  lazy val SF_STR_GENRE        = new Field(0x10)
  lazy val SF_STR_LAST: Field  = SF_STR_GENRE

//  class _4(val value: CInt) extends AnyVal
//  object _4 {
//    lazy val SF_FALSE              = new _4(0)
//    lazy val SF_TRUE               = new _4(1)
//    lazy val SFM_READ              = new _4(0x10)
//    lazy val SFM_WRITE             = new _4(0x20)
//    lazy val SFM_RDWR              = new _4(0x30)
//    lazy val SF_AMBISONIC_NONE     = new _4(0x40)
//    lazy val SF_AMBISONIC_B_FORMAT = new _4(0x41)
//  }
//
//  class _5(val value: CInt) extends AnyVal
//  object _5 {
//    lazy val SF_ERR_NO_ERROR             = new _5(0)
//    lazy val SF_ERR_UNRECOGNISED_FORMAT  = new _5(1)
//    lazy val SF_ERR_SYSTEM               = new _5(2)
//    lazy val SF_ERR_MALFORMED_FILE       = new _5(3)
//    lazy val SF_ERR_UNSUPPORTED_ENCODING = new _5(4)
//  }
//
//  class _6(val value: CInt) extends AnyVal
//  object _6 {
//    lazy val SF_CHANNEL_MAP_INVALID               = new _6(0)
//    lazy val SF_CHANNEL_MAP_MONO                  = new _6(1)
//    lazy val SF_CHANNEL_MAP_LEFT                  = new _6(2)
//    lazy val SF_CHANNEL_MAP_RIGHT                 = new _6(3)
//    lazy val SF_CHANNEL_MAP_CENTER                = new _6(4)
//    lazy val SF_CHANNEL_MAP_FRONT_LEFT            = new _6(5)
//    lazy val SF_CHANNEL_MAP_FRONT_RIGHT           = new _6(6)
//    lazy val SF_CHANNEL_MAP_FRONT_CENTER          = new _6(7)
//    lazy val SF_CHANNEL_MAP_REAR_CENTER           = new _6(8)
//    lazy val SF_CHANNEL_MAP_REAR_LEFT             = new _6(9)
//    lazy val SF_CHANNEL_MAP_REAR_RIGHT            = new _6(10)
//    lazy val SF_CHANNEL_MAP_LFE                   = new _6(11)
//    lazy val SF_CHANNEL_MAP_FRONT_LEFT_OF_CENTER  = new _6(12)
//    lazy val SF_CHANNEL_MAP_FRONT_RIGHT_OF_CENTER = new _6(13)
//    lazy val SF_CHANNEL_MAP_SIDE_LEFT             = new _6(14)
//    lazy val SF_CHANNEL_MAP_SIDE_RIGHT            = new _6(15)
//    lazy val SF_CHANNEL_MAP_TOP_CENTER            = new _6(16)
//    lazy val SF_CHANNEL_MAP_TOP_FRONT_LEFT        = new _6(17)
//    lazy val SF_CHANNEL_MAP_TOP_FRONT_RIGHT       = new _6(18)
//    lazy val SF_CHANNEL_MAP_TOP_FRONT_CENTER      = new _6(19)
//    lazy val SF_CHANNEL_MAP_TOP_REAR_LEFT         = new _6(20)
//    lazy val SF_CHANNEL_MAP_TOP_REAR_RIGHT        = new _6(21)
//    lazy val SF_CHANNEL_MAP_TOP_REAR_CENTER       = new _6(22)
//    lazy val SF_CHANNEL_MAP_AMBISONIC_B_W         = new _6(23)
//    lazy val SF_CHANNEL_MAP_AMBISONIC_B_X         = new _6(24)
//    lazy val SF_CHANNEL_MAP_AMBISONIC_B_Y         = new _6(25)
//    lazy val SF_CHANNEL_MAP_AMBISONIC_B_Z         = new _6(26)
//    lazy val SF_CHANNEL_MAP_MAX                   = new _6(27)
//  }

  case class SFInfo(frames: Long, samplerate: Int, channels: Int, format: FormatType, sections: Int, seekable: Boolean)

  implicit class SFInfoOps(val sf_info: Ptr[sf.SF_INFO]) extends AnyVal {
    def frames: Long       = sf_info._1
    def samplerate: Int    = sf_info._2
    def channels: Int      = sf_info._3
    def format: FormatType = new FormatType(sf_info._4)
    def sections: Int      = sf_info._5
    def seekable: Int      = sf_info._6

    def frames_=(v: Long): Unit       = sf_info._1 = v
    def samplerate_=(v: Int): Unit    = sf_info._2 = v
    def channels_=(v: Int): Unit      = sf_info._3 = v
    def format_=(v: FormatType): Unit = sf_info._4 = v.value
    def sections_=(v: Int): Unit      = sf_info._5 = v
    def seekable_=(v: Int): Unit      = sf_info._6 = v
  }

  implicit class SFError(val num: CInt) extends AnyVal

  lazy val SF_ERR_NO_ERROR: SFError             = SFError(0)
  lazy val SF_ERR_UNRECOGNISED_FORMAT: SFError  = SFError(1)
  lazy val SF_ERR_SYSTEM: SFError               = SFError(2)
  lazy val SF_ERR_MALFORMED_FILE: SFError       = SFError(3)
  lazy val SF_ERR_UNSUPPORTED_ENCODING: SFError = SFError(4)

  implicit class Sndfile(val sndfile: sf.SNDFILE) extends AnyVal {

    def sf_seek(frames: Int, whence: Whence): Int = sf_seekl(frames, whence).toInt

    def sf_seekl(frames: Long, whence: Whence): Long = sf.sf_seek(sndfile, frames, whence.value)

    def sf_error: SFError = sf.sf_error(sndfile)

    def strerror: String = fromCString(sf.sf_strerror(sndfile))

    def sf_error_number(error: SFError): String = fromCString(sf.sf_error_number(error.num))

    def close: SFError = sf.sf_close(sndfile)

    def sf_write_sync(): Unit = sf.sf_write_sync(sndfile)

    def read_short(dst: mutable.Seq[Short], items: Int): Int = Zone { implicit z =>
      val buf   = if (items > 1024) alloc[CShort](items.toUInt) else stackalloc[CShort](items.toUInt)
      val count = sf.sf_read_short(sndfile, buf, items).toInt

      for (i <- 0 until count)
        dst(i) = buf(i)

      count
    }

    def read_int(dst: mutable.Seq[Int], items: Int): Int = Zone { implicit z =>
      val buf   = if (items > 1024) alloc[CInt](items.toUInt) else stackalloc[CInt](items.toUInt)
      val count = sf.sf_read_int(sndfile, buf, items).toInt

      for (i <- 0 until count)
        dst(i) = buf(i)

      count
    }

    def read_float(dst: mutable.Seq[Float], items: Int): Int = Zone { implicit z =>
      val buf   = if (items > 1024) alloc[CFloat](items.toUInt) else stackalloc[CFloat](items.toUInt)
      val count = sf.sf_read_float(sndfile, buf, items).toInt

      for (i <- 0 until count)
        dst(i) = buf(i)

      count
    }

    def read_double(dst: mutable.Seq[Double], items: Int): Int = Zone { implicit z =>
      val buf   = if (items > 1024) alloc[CDouble](items.toUInt) else stackalloc[CDouble](items.toUInt)
      val count = sf.sf_read_double(sndfile, buf, items).toInt

      for (i <- 0 until count)
        dst(i) = buf(i)

      count
    }

    def readf_short(dst: mutable.Seq[Short], frames: Int): Int = Zone { implicit z =>
      val sfinfo = stackalloc[sf.SF_INFO]

      sf.sf_command(sndfile, SFC_GET_CURRENT_SF_INFO.value, sfinfo.asInstanceOf[Ptr[Byte]], sizeof[sf.SF_INFO].toInt)

      val channels = sfinfo.channels
      val samples  = channels * frames
      val buf      = if (frames > 1024) alloc[CShort](samples.toUInt) else stackalloc[CShort](samples.toUInt)
      val count    = sf.sf_readf_short(sndfile, buf, frames).toInt

      for (i <- 0 until channels * count)
        dst(i) = buf(i)

      count
    }

    def readf_int(dst: mutable.Seq[Int], frames: Int): Int = Zone { implicit z =>
      val sfinfo = stackalloc[sf.SF_INFO]

      sf.sf_command(sndfile, SFC_GET_CURRENT_SF_INFO.value, sfinfo.asInstanceOf[Ptr[Byte]], sizeof[sf.SF_INFO].toInt)

      val channels = sfinfo.channels
      val samples  = channels * frames
      val buf      = if (frames > 1024) alloc[CInt](samples.toUInt) else stackalloc[CInt](samples.toUInt)
      val count    = sf.sf_readf_int(sndfile, buf, frames).toInt

      for (i <- 0 until channels * count)
        dst(i) = buf(i)

      count
    }

    def readf_float(dst: mutable.Seq[Float], frames: Int): Int = Zone { implicit z =>
      val sfinfo = stackalloc[sf.SF_INFO]

      sf.sf_command(sndfile, SFC_GET_CURRENT_SF_INFO.value, sfinfo.asInstanceOf[Ptr[Byte]], sizeof[sf.SF_INFO].toInt)

      val channels = sfinfo.channels
      val samples  = channels * frames
      val buf      = if (frames > 1024) alloc[CFloat](samples.toUInt) else stackalloc[CFloat](samples.toUInt)
      val count    = sf.sf_readf_float(sndfile, buf, frames).toInt

      for (i <- 0 until channels * count)
        dst(i) = buf(i)

      count
    }

    def readf_double(dst: mutable.Seq[Double], frames: Int): Int = Zone { implicit z =>
      val sfinfo = stackalloc[sf.SF_INFO]

      sf.sf_command(sndfile, SFC_GET_CURRENT_SF_INFO.value, sfinfo.asInstanceOf[Ptr[Byte]], sizeof[sf.SF_INFO].toInt)

      val channels = sfinfo.channels
      val samples  = channels * frames
      val buf      = if (frames > 1024) alloc[CDouble](samples.toUInt) else stackalloc[CDouble](samples.toUInt)
      val count    = sf.sf_readf_double(sndfile, buf, frames).toInt

      for (i <- 0 until channels * count)
        dst(i) = buf(i)

      count
    }

    def write_short(src: Int => Short, items: Int): Int = Zone { implicit z =>
      val buf = if (items > 1024) alloc[CShort](items.toUInt) else stackalloc[CShort](items.toUInt)

      for (i <- 0 until items)
        buf(i) = src(i)

      sf.sf_write_short(sndfile, buf, items).toInt
    }

    def write_int(src: Int => Int, items: Int): Int = Zone { implicit z =>
      val buf = if (items > 1024) alloc[CInt](items.toUInt) else stackalloc[CInt](items.toUInt)

      for (i <- 0 until items)
        buf(i) = src(i)

      sf.sf_write_int(sndfile, buf, items).toInt
    }

    def write_float(src: Int => Float, items: Int): Int = Zone { implicit z =>
      val buf = if (items > 1024) alloc[CFloat](items.toUInt) else stackalloc[CFloat](items.toUInt)

      for (i <- 0 until items)
        buf(i) = src(i)

      sf.sf_write_float(sndfile, buf, items).toInt
    }

    def write_double(src: Int => Double, items: Int): Int = Zone { implicit z =>
      val buf = if (items > 1024) alloc[CDouble](items.toUInt) else stackalloc[CDouble](items.toUInt)

      for (i <- 0 until items)
        buf(i) = src(i)

      sf.sf_write_double(sndfile, buf, items).toInt
    }

    def writef_short(src: Int => Short, frames: Int): Int = Zone { implicit z =>
      val sfinfo = stackalloc[sf.SF_INFO]

      sf.sf_command(sndfile, SFC_GET_CURRENT_SF_INFO.value, sfinfo.asInstanceOf[Ptr[Byte]], sizeof[sf.SF_INFO].toInt)

      val channels = sfinfo.channels
      val samples  = channels * frames
      val buf      = if (frames > 1024) alloc[CShort](samples.toUInt) else stackalloc[CShort](samples.toUInt)

      for (i <- 0 until frames)
        buf(i) = src(i)

      sf.sf_writef_short(sndfile, buf, frames).toInt
    }

    def writef_int(src: Int => Int, frames: Int): Int = Zone { implicit z =>
      val sfinfo = stackalloc[sf.SF_INFO]

      sf.sf_command(sndfile, SFC_GET_CURRENT_SF_INFO.value, sfinfo.asInstanceOf[Ptr[Byte]], sizeof[sf.SF_INFO].toInt)

      val channels = sfinfo.channels
      val samples  = channels * frames
      val buf      = if (frames > 1024) alloc[CInt](samples.toUInt) else stackalloc[CInt](samples.toUInt)

      for (i <- 0 until frames)
        buf(i) = src(i)

      sf.sf_writef_int(sndfile, buf, frames).toInt
    }

    def writef_float(src: Int => Float, frames: Int): Int = Zone { implicit z =>
      val sfinfo = stackalloc[sf.SF_INFO]

      sf.sf_command(sndfile, SFC_GET_CURRENT_SF_INFO.value, sfinfo.asInstanceOf[Ptr[Byte]], sizeof[sf.SF_INFO].toInt)

      val channels = sfinfo.channels
      val samples  = channels * frames
      val buf      = if (frames > 1024) alloc[CFloat](samples.toUInt) else stackalloc[CFloat](samples.toUInt)

      for (i <- 0 until frames)
        buf(i) = src(i)

      sf.sf_writef_float(sndfile, buf, frames).toInt
    }

    def writef_double(src: Int => Double, frames: Int): Int = Zone { implicit z =>
      val sfinfo = stackalloc[sf.SF_INFO]

      sf.sf_command(sndfile, SFC_GET_CURRENT_SF_INFO.value, sfinfo.asInstanceOf[Ptr[Byte]], sizeof[sf.SF_INFO].toInt)

      val channels = sfinfo.channels
      val samples  = channels * frames
      val buf      = if (frames > 1024) alloc[CDouble](samples.toUInt) else stackalloc[CDouble](samples.toUInt)

      for (i <- 0 until frames)
        buf(i) = src(i)

      sf.sf_writef_double(sndfile, buf, frames).toInt
    }

    def sf_get_string(field: Field): String = fromCString(sf.sf_get_string(sndfile, field.value))

    def sf_set_string(field: Field, str: String): SFError =
      Zone(implicit z => sf.sf_set_string(sndfile, field.value, toCString(str)))

    def sf_current_byterate: Int = sf.sf_current_byterate(sndfile)

//    def sf_command(cmd: Command, data: Ptr[Byte], datasize: CInt): CInt = sf.sf_command(sndfile, cmd.value)
    def getCurrentSFInfo: SFInfo = {
      val sfinfo = stackalloc[sf.SF_INFO]

      sf.sf_command(sndfile, SFC_GET_CURRENT_SF_INFO.value, sfinfo.asInstanceOf[Ptr[Byte]], sizeof[sf.SF_INFO].toInt)
      SFInfo(sfinfo.frames,
             sfinfo.samplerate,
             sfinfo.channels,
             sfinfo.format,
             sfinfo.sections,
             if (sfinfo.seekable == 0) false else true)
    }

  }

  def sf_open(path: String, mode: Mode, sfinfo: SFInfo = SFInfo(0, 0, 0, 0, 0, seekable = false)): (Sndfile, SFInfo) =
    Zone { implicit z =>
      val sfinfop = stackalloc[sf.SF_INFO]

      sfinfop.frames = sfinfo.frames
      sfinfop.samplerate = sfinfo.samplerate
      sfinfop.channels = sfinfo.channels
      sfinfop.format = sfinfo.format
      sfinfop.sections = sfinfo.sections
      sfinfop.seekable = if (sfinfo.seekable) 1 else 0
      (sf.sf_open(toCString(path), mode.value, sfinfop),
       SFInfo(sfinfop.frames,
              sfinfop.samplerate,
              sfinfop.channels,
              sfinfop.format,
              sfinfop.sections,
              if (sfinfop.seekable == 0) false else true))
    }

  def sf_format_check(sfinfo: SFInfo): Boolean = Zone { implicit z =>
    val sfinfop = stackalloc[sf.SF_INFO]

    sfinfop.frames = sfinfo.frames
    sfinfop.samplerate = sfinfo.samplerate
    sfinfop.channels = sfinfo.channels
    sfinfop.format = sfinfo.format
    sfinfop.sections = sfinfo.sections
    sfinfop.seekable = if (sfinfo.seekable) 1 else 0

    if (sf.sf_format_check(sfinfop) == 0) false
    else true
  }

  def sf_version_string: String = fromCString(sf.sf_version_string)

}
