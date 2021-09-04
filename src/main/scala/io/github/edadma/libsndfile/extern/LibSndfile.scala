package io.github.edadma.libsndfile.extern

import scala.scalanative.unsafe.Nat._
import scala.scalanative.unsafe._

@link("sndfile")
@extern
object LibSndfile {

  type sf_count_t     = CLong
  type SNDFILE        = Ptr[Byte]
  type CHUNK_ITERATOR = Ptr[Byte]
  type INFO           = CStruct6[sf_count_t, CInt, CInt, CInt, CInt, CInt]
  type CHUNK_INFO     = CStruct4[CArray[CChar, Digit2[_6, _4]], CUnsignedInt, CUnsignedInt, Ptr[Byte]]

  def sf_open(path: CString, mode: CInt, sfinfo: Ptr[INFO]): SNDFILE                        = extern
  def sf_format_check(sfinfo: Ptr[INFO]): CInt                                              = extern
  def sf_seek(sndfile: SNDFILE, frames: sf_count_t, whence: CInt): sf_count_t               = extern
  def sf_error(sndfile: SNDFILE): CInt                                                      = extern
  def sf_strerror(sndfile: SNDFILE): CString                                                = extern
  def sf_error_number(errnum: CInt): CString                                                = extern
  def sf_close(sndfile: SNDFILE): CInt                                                      = extern
  def sf_write_sync(sndfile: SNDFILE): Unit                                                 = extern
  def sf_read_short(sndfile: SNDFILE, ptr: Ptr[CShort], items: sf_count_t): sf_count_t      = extern
  def sf_read_int(sndfile: SNDFILE, ptr: Ptr[CInt], items: sf_count_t): sf_count_t          = extern
  def sf_read_float(sndfile: SNDFILE, ptr: Ptr[CFloat], items: sf_count_t): sf_count_t      = extern
  def sf_read_double(sndfile: SNDFILE, ptr: Ptr[CDouble], items: sf_count_t): sf_count_t    = extern
  def sf_readf_short(sndfile: SNDFILE, ptr: Ptr[CShort], frames: sf_count_t): sf_count_t    = extern
  def sf_readf_int(sndfile: SNDFILE, ptr: Ptr[CInt], frames: sf_count_t): sf_count_t        = extern
  def sf_readf_float(sndfile: SNDFILE, ptr: Ptr[CFloat], frames: sf_count_t): sf_count_t    = extern
  def sf_readf_double(sndfile: SNDFILE, ptr: Ptr[CDouble], frames: sf_count_t): sf_count_t  = extern
  def sf_write_short(sndfile: SNDFILE, ptr: Ptr[CShort], items: sf_count_t): sf_count_t     = extern
  def sf_write_int(sndfile: SNDFILE, ptr: Ptr[CInt], items: sf_count_t): sf_count_t         = extern
  def sf_write_float(sndfile: SNDFILE, ptr: Ptr[CFloat], items: sf_count_t): sf_count_t     = extern
  def sf_write_double(sndfile: SNDFILE, ptr: Ptr[CDouble], items: sf_count_t): sf_count_t   = extern
  def sf_writef_short(sndfile: SNDFILE, ptr: Ptr[CShort], frames: sf_count_t): sf_count_t   = extern
  def sf_writef_int(sndfile: SNDFILE, ptr: Ptr[CInt], frames: sf_count_t): sf_count_t       = extern
  def sf_writef_float(sndfile: SNDFILE, ptr: Ptr[CFloat], frames: sf_count_t): sf_count_t   = extern
  def sf_writef_double(sndfile: SNDFILE, ptr: Ptr[CDouble], frames: sf_count_t): sf_count_t = extern
  def sf_get_string(sndfile: SNDFILE, field: CInt): CString                                 = extern
  def sf_set_string(sndfile: SNDFILE, field: CInt, str: CString): CInt                      = extern
  def sf_version_string: CString                                                            = extern
  def sf_current_byterate(sndfile: SNDFILE): CInt                                           = extern
  def sf_get_chunk_iterator(sndfile: SNDFILE, chuck_info: Ptr[CHUNK_INFO]): CHUNK_ITERATOR  = extern

  def sf_command(sndfile: SNDFILE, cmd: CInt, data: Ptr[Byte], datasize: CInt): CInt = extern

}
