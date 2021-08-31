package io.github.edadma.libsndfile.extern

import scala.scalanative.unsafe._

@link("sndfile")
@extern
object LibSndfile {
  type sf_count_t = CLong

  type SNDFILE = Ptr[Byte]

  type SF_INFO = CStruct6[sf_count_t, CInt, CInt, CInt, CInt, CInt]

  def sf_open(path: CString, mode: CInt, sfinfo: Ptr[SF_INFO]): Ptr[SNDFILE] = extern

  def sf_write_int(sndfile: Ptr[SNDFILE], ptr: Ptr[CInt], items: sf_count_t): sf_count_t = extern

  def sf_close(sndfile: Ptr[SNDFILE]): CInt = extern

  def sf_strerror(sndfile: Ptr[SNDFILE]): CString = extern

}
