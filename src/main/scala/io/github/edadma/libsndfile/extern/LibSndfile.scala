package io.github.edadma.libsndfile.extern

import scala.scalanative.unsafe._

@link("sndfile")
@extern
object LibSndfile {
  type sf_count_t = CLong

  type SNDFILE = Ptr[Byte]

  type SF_INFO = CStruct6[sf_count_t, CInt, CInt, CInt, CInt, CInt]

  def f_open(path: CString, mode: CInt, sfinfo: Ptr[SF_INFO]): Ptr[SNDFILE] = extern

}