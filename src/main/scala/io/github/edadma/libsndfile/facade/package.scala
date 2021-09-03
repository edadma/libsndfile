package io.github.edadma.libsndfile

import io.github.edadma.libsndfile.extern.{LibSndfile => sf}

import scala.scalanative.unsafe._
import scala.scalanative.unsigned._

package object facade {

  class FormatType(val value: CInt) extends AnyVal {
    def subtype(subtype: FormatSubtype): FormatType = new FormatType(value | subtype.value)
    def endian(endian: Endian): FormatType          = new FormatType(value | endian.value)
  }

  lazy val SF_FORMAT_WAV   = new FormatType(0x10000)
  lazy val SF_FORMAT_AIFF  = new FormatType(0x20000)
  lazy val SF_FORMAT_AU    = new FormatType(0x30000)
  lazy val SF_FORMAT_RAW   = new FormatType(0x40000)
  lazy val SF_FORMAT_PAF   = new FormatType(0x50000)
  lazy val SF_FORMAT_SVX   = new FormatType(0x60000)
  lazy val SF_FORMAT_NIST  = new FormatType(0x70000)
  lazy val SF_FORMAT_VOC   = new FormatType(0x80000)
  lazy val SF_FORMAT_IRCAM = new FormatType(0xa0000)
  lazy val SF_FORMAT_W64   = new FormatType(0xb0000)
  lazy val SF_FORMAT_MAT4  = new FormatType(0xc0000)
  lazy val SF_FORMAT_MAT5  = new FormatType(0xd0000)
  lazy val SF_FORMAT_PVF   = new FormatType(0xe0000)
  lazy val SF_FORMAT_XI    = new FormatType(0xf0000)
  lazy val SF_FORMAT_HTK   = new FormatType(0x100000)
  lazy val SF_FORMAT_SDS   = new FormatType(0x110000)
  lazy val SF_FORMAT_AVR   = new FormatType(0x120000)
  lazy val SF_FORMAT_WAVEX = new FormatType(0x130000)
  lazy val SF_FORMAT_SD2   = new FormatType(0x160000)
  lazy val SF_FORMAT_FLAC  = new FormatType(0x170000)
  lazy val SF_FORMAT_CAF   = new FormatType(0x180000)
  lazy val SF_FORMAT_WVE   = new FormatType(0x190000)
  lazy val SF_FORMAT_OGG   = new FormatType(0x200000)
  lazy val SF_FORMAT_MPC2K = new FormatType(0x210000)
  lazy val SF_FORMAT_RF64  = new FormatType(0x220000)

  class FormatSubtype(val value: CInt) extends AnyVal

  lazy val SF_FORMAT_PCM_S8       = new FormatSubtype(0x1)
  lazy val SF_FORMAT_PCM_16       = new FormatSubtype(0x2)
  lazy val SF_FORMAT_PCM_24       = new FormatSubtype(0x3)
  lazy val SF_FORMAT_PCM_32       = new FormatSubtype(0x4)
  lazy val SF_FORMAT_PCM_U8       = new FormatSubtype(0x5)
  lazy val SF_FORMAT_FLOAT        = new FormatSubtype(0x6)
  lazy val SF_FORMAT_DOUBLE       = new FormatSubtype(0x7)
  lazy val SF_FORMAT_ULAW         = new FormatSubtype(0x10)
  lazy val SF_FORMAT_ALAW         = new FormatSubtype(0x11)
  lazy val SF_FORMAT_IMA_ADPCM    = new FormatSubtype(0x12)
  lazy val SF_FORMAT_MS_ADPCM     = new FormatSubtype(0x13)
  lazy val SF_FORMAT_GSM610       = new FormatSubtype(0x20)
  lazy val SF_FORMAT_VOX_ADPCM    = new FormatSubtype(0x21)
  lazy val SF_FORMAT_NMS_ADPCM_16 = new FormatSubtype(0x22)
  lazy val SF_FORMAT_NMS_ADPCM_24 = new FormatSubtype(0x23)
  lazy val SF_FORMAT_NMS_ADPCM_32 = new FormatSubtype(0x24)
  lazy val SF_FORMAT_G721_32      = new FormatSubtype(0x30)
  lazy val SF_FORMAT_G723_24      = new FormatSubtype(0x31)
  lazy val SF_FORMAT_G723_40      = new FormatSubtype(0x32)
  lazy val SF_FORMAT_DWVW_12      = new FormatSubtype(0x40)
  lazy val SF_FORMAT_DWVW_16      = new FormatSubtype(0x41)
  lazy val SF_FORMAT_DWVW_24      = new FormatSubtype(0x42)
  lazy val SF_FORMAT_DWVW_N       = new FormatSubtype(0x43)
  lazy val SF_FORMAT_DPCM_8       = new FormatSubtype(0x50)
  lazy val SF_FORMAT_DPCM_16      = new FormatSubtype(0x51)
  lazy val SF_FORMAT_VORBIS       = new FormatSubtype(0x60)
  lazy val SF_FORMAT_OPUS         = new FormatSubtype(0x64)
  lazy val SF_FORMAT_ALAC_16      = new FormatSubtype(0x70)
  lazy val SF_FORMAT_ALAC_20      = new FormatSubtype(0x71)
  lazy val SF_FORMAT_ALAC_24      = new FormatSubtype(0x72)
  lazy val SF_FORMAT_ALAC_32      = new FormatSubtype(0x73)

  class Endian(val value: CInt) extends AnyVal

  lazy val SF_ENDIAN_FILE   = new Endian(0x0)
  lazy val SF_ENDIAN_LITTLE = new Endian(0x10000000)
  lazy val SF_ENDIAN_BIG    = new Endian(0x20000000)
  lazy val SF_ENDIAN_CPU    = new Endian(0x30000000)

  class Mode(val value: CInt) extends AnyVal

  lazy val SFM_READ  = new Mode(0x10)
  lazy val SFM_WRITE = new Mode(0x20)
  lazy val SFM_RDWR  = new Mode(0x30)

  //  lazy val SF_FORMAT_SUBMASK  = 0xffff
//  lazy val SF_FORMAT_TYPEMASK = 0xfff0000)
//  lazy val SF_FORMAT_ENDMASK  = 0x30000000)

//  class _2(val value: CInt) extends AnyVal
//  object _2 {
//    lazy val SFC_GET_LIB_VERSION            = new _2(0x1000)
//    lazy val SFC_GET_LOG_INFO               = new _2(0x1001)
//    lazy val SFC_GET_CURRENT_SF_INFO        = new _2(0x1002)
//    lazy val SFC_GET_NORM_DOUBLE            = new _2(0x1010)
//    lazy val SFC_GET_NORM_FLOAT             = new _2(0x1011)
//    lazy val SFC_SET_NORM_DOUBLE            = new _2(0x1012)
//    lazy val SFC_SET_NORM_FLOAT             = new _2(0x1013)
//    lazy val SFC_SET_SCALE_FLOAT_INT_READ   = new _2(0x1014)
//    lazy val SFC_SET_SCALE_INT_FLOAT_WRITE  = new _2(0x1015)
//    lazy val SFC_GET_SIMPLE_FORMAT_COUNT    = new _2(0x1020)
//    lazy val SFC_GET_SIMPLE_FORMAT          = new _2(0x1021)
//    lazy val SFC_GET_FORMAT_INFO            = new _2(0x1028)
//    lazy val SFC_GET_FORMAT_MAJOR_COUNT     = new _2(0x1030)
//    lazy val SFC_GET_FORMAT_MAJOR           = new _2(0x1031)
//    lazy val SFC_GET_FORMAT_SUBTYPE_COUNT   = new _2(0x1032)
//    lazy val SFC_GET_FORMAT_SUBTYPE         = new _2(0x1033)
//    lazy val SFC_CALC_SIGNAL_MAX            = new _2(0x1040)
//    lazy val SFC_CALC_NORM_SIGNAL_MAX       = new _2(0x1041)
//    lazy val SFC_CALC_MAX_ALL_CHANNELS      = new _2(0x1042)
//    lazy val SFC_CALC_NORM_MAX_ALL_CHANNELS = new _2(0x1043)
//    lazy val SFC_GET_SIGNAL_MAX             = new _2(0x1044)
//    lazy val SFC_GET_MAX_ALL_CHANNELS       = new _2(0x1045)
//    lazy val SFC_SET_ADD_PEAK_CHUNK         = new _2(0x1050)
//    lazy val SFC_UPDATE_HEADER_NOW          = new _2(0x1060)
//    lazy val SFC_SET_UPDATE_HEADER_AUTO     = new _2(0x1061)
//    lazy val SFC_FILE_TRUNCATE              = new _2(0x1080)
//    lazy val SFC_SET_RAW_START_OFFSET       = new _2(0x1090)
//    lazy val SFC_SET_DITHER_ON_WRITE        = new _2(0x10a0)
//    lazy val SFC_SET_DITHER_ON_READ         = new _2(0x10a1)
//    lazy val SFC_GET_DITHER_INFO_COUNT      = new _2(0x10a2)
//    lazy val SFC_GET_DITHER_INFO            = new _2(0x10a3)
//    lazy val SFC_GET_EMBED_FILE_INFO        = new _2(0x10b0)
//    lazy val SFC_SET_CLIPPING               = new _2(0x10c0)
//    lazy val SFC_GET_CLIPPING               = new _2(0x10c1)
//    lazy val SFC_GET_CUE_COUNT              = new _2(0x10cd)
//    lazy val SFC_GET_CUE                    = new _2(0x10ce)
//    lazy val SFC_SET_CUE                    = new _2(0x10cf)
//    lazy val SFC_GET_INSTRUMENT             = new _2(0x10d0)
//    lazy val SFC_SET_INSTRUMENT             = new _2(0x10d1)
//    lazy val SFC_GET_LOOP_INFO              = new _2(0x10e0)
//    lazy val SFC_GET_BROADCAST_INFO         = new _2(0x10f0)
//    lazy val SFC_SET_BROADCAST_INFO         = new _2(0x10f1)
//    lazy val SFC_GET_CHANNEL_MAP_INFO       = new _2(0x1100)
//    lazy val SFC_SET_CHANNEL_MAP_INFO       = new _2(0x1101)
//    lazy val SFC_RAW_DATA_NEEDS_ENDSWAP     = new _2(0x1110)
//    lazy val SFC_WAVEX_SET_AMBISONIC        = new _2(0x1200)
//    lazy val SFC_WAVEX_GET_AMBISONIC        = new _2(0x1201)
//    lazy val SFC_RF64_AUTO_DOWNGRADE        = new _2(0x1210)
//    lazy val SFC_SET_VBR_ENCODING_QUALITY   = new _2(0x1300)
//    lazy val SFC_SET_COMPRESSION_LEVEL      = new _2(0x1301)
//    lazy val SFC_SET_OGG_PAGE_LATENCY_MS    = new _2(0x1302)
//    lazy val SFC_SET_OGG_PAGE_LATENCY       = new _2(0x1303)
//    lazy val SFC_SET_CART_INFO              = new _2(0x1400)
//    lazy val SFC_GET_CART_INFO              = new _2(0x1401)
//    lazy val SFC_SET_ORIGINAL_SAMPLERATE    = new _2(0x1500)
//    lazy val SFC_GET_ORIGINAL_SAMPLERATE    = new _2(0x1501)
//    lazy val SFC_TEST_IEEE_FLOAT_REPLACE    = new _2(0x6001)
//    lazy val SFC_SET_ADD_HEADER_PAD_CHUNK   = new _2(0x1051)
//    lazy val SFC_SET_ADD_DITHER_ON_WRITE    = new _2(0x1070)
//    lazy val SFC_SET_ADD_DITHER_ON_READ     = new _2(0x1071)
//  }
//
//  class _3(val value: CInt) extends AnyVal
//  object _3 {
//    lazy val SF_STR_TITLE       = new _3(0x1)
//    lazy val SF_STR_COPYRIGHT   = new _3(0x2)
//    lazy val SF_STR_SOFTWARE    = new _3(0x3)
//    lazy val SF_STR_ARTIST      = new _3(0x4)
//    lazy val SF_STR_COMMENT     = new _3(0x5)
//    lazy val SF_STR_DATE        = new _3(0x6)
//    lazy val SF_STR_ALBUM       = new _3(0x7)
//    lazy val SF_STR_LICENSE     = new _3(0x8)
//    lazy val SF_STR_TRACKNUMBER = new _3(0x9)
//    lazy val SF_STR_GENRE       = new _3(0x10)
//  }
//
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

  case class SFInfo(frames: Long,
                    samplerate: Int,
                    channels: Int,
                    format: FormatType,
                    sections: Int = 0,
                    seekable: Int = 0)

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

    def write_int(f: Int => Int, items: Int): Int = Zone { implicit z =>
      val buf = alloc[CInt](items.toULong)

      for (i <- 0 until items)
        buf(i) = f(i)

      sf.sf_write_int(sndfile, buf, items).toInt
    }

    def sf_error: SFError = sf.sf_error(sndfile)

    def strerror: String = fromCString(sf.sf_strerror(sndfile))

    def sf_error_number(error: SFError): String = fromCString(sf.sf_error_number(error.num))

    def close: SFError = sf.sf_close(sndfile)

    def sf_write_sync(): Unit = sf.sf_write_sync(sndfile)

  }

  def sf_open(path: String, mode: Mode, sfinfo: SFInfo): (Sndfile, SFInfo) = Zone { implicit z =>
    val sfinfop = stackalloc[sf.SF_INFO]

    sfinfop.frames = sfinfo.frames
    sfinfop.samplerate = sfinfo.samplerate
    sfinfop.channels = sfinfo.channels
    sfinfop.format = sfinfo.format
    sfinfop.sections = sfinfo.sections
    sfinfop.seekable = sfinfo.seekable
    (sf.sf_open(toCString(path), mode.value, sfinfop),
     SFInfo(sfinfop.frames, sfinfop.samplerate, sfinfop.channels, sfinfop.format, sfinfop.sections, sfinfop.seekable))
  }

  def sf_format_check(sfinfo: SFInfo): Boolean = Zone { implicit z =>
    val sfinfop = stackalloc[sf.SF_INFO]

    sfinfop.frames = sfinfo.frames
    sfinfop.samplerate = sfinfo.samplerate
    sfinfop.channels = sfinfo.channels
    sfinfop.format = sfinfo.format
    sfinfop.sections = sfinfo.sections
    sfinfop.seekable = sfinfo.seekable

    if (sf.sf_format_check(sfinfop) == 0) false
    else true
  }

}
