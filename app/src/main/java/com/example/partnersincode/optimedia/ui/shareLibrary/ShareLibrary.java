package com.example.partnersincode.optimedia.ui.shareLibrary;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.example.partnersincode.optimedia.R;

public class ShareLibrary extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.share_library,container, false);

        Button btnQR = view.findViewById(R.id.btnGenQR);
        btnQR.setOnClickListener(v->
        {
            //Create bundle to send to QR code fragment. TODO: Pass the XML as the correct datatype
            Bundle bundle = new Bundle();
            //Test string
            bundle.putString("XML","8ZtVYoSPj8ZAceKpl9hE" +
                            "UM5ojHm852EnwBnVmD7f" +
                            "Qc0Mj9VEVYwlP4WzsmIA" +
                            "yVYo8sBGpwBi5NQWtueQ" +
                            "pVDD9diwIHE2X0GzoPer" +
                            "vv2tvMAJdEAZKBzH5WHi" +
                            "OHkAUQgtbr3BsclIuIl4" +
                            "BzUG3ql2FrKRmkeGx1Za" +
                            "UCvaaKo9tK7Ve6ZkaZxM" +
                            "bn4vbeLtzQtKWpdZEbHJ" +
                            "uY2LCEIMi8LZTBKlIcNP" +
                            "x9xmORHjTGCCftHdK7Yi" +
                            "il5HwGYAkXEozC43Q3Cx" +
                            "KT0PmzlmXSocl6UcYHGS" +
                            "vAloyknlkw7V2z5M9Xc1" +
                            "QyohMtcy1cFPiEcm3pSi" +
                            "GVFHUGWSoJMhMkMHyDnX" +
                            "l721XXV9CfABVlV4yjaK" +
                            "zjAH2RPjWV1DoTBdW1ax" +
                            "meekEUVGDmMqKiHsSWNv" +
                            "i94lYifW5Hw3m8JBl8dW" +
                            "Ne2ZhKY311c6hLbi9pjI" +
                            "ZvCCi4DFd7Gcw4qKRV4A" +
                            "NBvrHNW01vxPpWupiztC" +
                            "0Jt8xBuTeVISUFkWp2Hm" +
                            "9sJ9RuHrZvTLUYtgI61i" +
                            "LHLyV3UXGoZWoDjIXmGG" +
                            "XipDHOVvwj00O7P6Wbzi" +
                            "YDR2LpyuxxwybdZLHKIJ" +
                            "yCL757hxbniAmWa6cHki" +
                            "Ww2BgVsJxhNNjfiqfp8t" +
                            "OxiDs0cA6OV28CFL6zPW" +
                            "QnWzBbAAlMyQFb7ThRf9" +
                            "cQ5VD0XEUg2btizo5a21" +
                            "B0FqbhhzjjFwaagjOIoV" +
                            "xsl4dY9xF2IiHooVR7M9" +
                            "0ceLlvQxUCkEziVohqGh" +
                            "8Dxx0j8MpwBen9u0KUyq" +
                            "lpxS7S7q1rtjxaCrPTMo" +
                            "5OrVRVnyRyC34A3fFqrT" +
                            "0jJmZMDwfr2zQGqlkv6v" +
                            "bTcFNHXnkEAnI3HSZ7rP" +
                            "lhvzhZpmmtx7hhzItmgT" +
                            "D383AVx50l7zLc2cJTqS" +
                            "LEIzDpDvsjsPD2RAoYG1" +
                            "wGUo1F9qziR6ZRQ7QMbK" +
                            "6v1DjQCJ21AdRBwVx1Zx" +
                            "OWmLPOIiHp6c1HcMZS59" +
                            "00Sai3myYViv9VNCvgWl" +
                            "BxBBHabHVor0MwxiTaJq" +
                            "7AL9z0KQQ7soqfuiUpyt" +
                            "9GxYEfSbRPhnE4DrWnPj" +
                            "3VlCINC2ZhY1HHj7DLXa" +
                            "ayW9P5nL9d5WEvgOPyoA" +
                            "W925WeSHioxPDnXOMbA6" +
                            "jOqAGgKoFaZFSifvbCmK" +
                            "h4L4R21JlHY6SzGFIIU1" +
                            "KS50ooAe9J68JgDOCYwI" +
                            "uuBMbAonlGIFOYoxmDd9" +
                            "NhrzIYpSpmojGLnPF97n" +
                            "xXUUBVDjhw4or1v5eQXM" +
                            "mSrGqLAvcCLVhXjIoFDF" +
                            "n2Cd9TR1a6PBIHrYvduB" +
                            "dj8rmLCEXdXNvDIjobgF" +
                            "uFIbBF8uqz6heukj4JG6" +
                            "AFCtgWxuZLcCxWco125s" +
                            "aXj2Yf9E5bBQSD9EMx8e" +
                            "Ry0lyY3tVjSxj7Z5QD0f" +
                            "sx19GgWTfLk3papxzZN7" +
                            "Faa1lawxQqC5ZZjg7ugZ" +
                            "EvP2XiSpvmkPReOROdOj" +
                            "TjYc2OA5tDimYfl7rhxB" +
                            "UdQnlu8PQi3SPDZ4WYs5" +
                            "YrBWB5DrtgcjNRqA52RA" +
                            "bTMwI30D2SjGZ7HaZl6H" +
                            "kmvVi5p3WOG937GXQ1Xx" +
                            "aqc56yTCnPoaeQuzb04d" +
                            "ZsvgS5fvfRHQw0V8vY0G" +
                            "2N3rxCBRYGnoiGQJfl9a" +
                            "CyUFkmGzaxxbhQfFJZaV" +
                            "kTCuptKVOVatOWApgkJe" +
                            "6uof7zsSnDCyBo616MUX" +
                            "1doXFXdyUHUl2DIBMuYK" +
                            "rSrN4Lwn6VRxDluD5OPy" +
                            "jUZ5WGEfOK9l7Jh3IlL4" +
                            "6JN64N9uYU0B685ySvR7" +
                            "gdD8oNEgMlyojPb8WxZo" +
                            "geS3eGsDCka1vJiVuiCk" +
                            "vzjkEG2aIk4J3KHyLTpx" +
                            "6cIW13ITIswSSaLXOUPY" +
                            "SESy6auV1aSeAFPgmTEa" +
                            "dUGKvg6RWxYil6ughzex" +
                            "uU24DzZkpCLT3paD3fxj" +
                            "7lvOT9rihNibj3xNS21W" +
                            "9TYfPMJZe19IQN6KI16Q" +
                            "H1tTB17P15LMkaIPrl09" +
                            "P73iFFySRQaKRUiwYpUa" +
                            "ULP8ow8rOcwnEtnDW38m" +
                            "s475D9z4iPvFB2KriQr5" +
                            "sNAZUdpwVxPSm4qulzgP" +
                            "MObX96zvViMpABAu7yCN" +
                            "5GTTbW1B9APBAKLabd0L" +
                            "8ecpmkcAbJCjMi3sap0Q" +
                            "bqx0NwObmACzL60xquNj" +
                            "tmkWQWcHzwz2Po2SBOfK" +
                            "22KiWCHUAsp3SLOIwRNm" +
                            "hqDgSHjSfVPC8WzR8z0H" +
                            "kLVFmfpDn66x9Ch3uBD2" +
                            "9g53cq007ED1VYii3ViO" +
                            "QyuOJ8cShk3ZV8ML7V2q" +
                            "Xx9agDE86cjXJAWUtGFp" +
                            "nnQcBgHQqARUpl4AiVMt" +
                            "09PMsWQdoGZTa507Wse1" +
                            "U7CL5QrlQ5LVzXhPCJT1" +
                            "nnQW6gXrtPnZIW7mEbkr" +
                            "Sh1NrsnJwkufQhd6MrAR" +
                            "ZSW79XGK7mf4zCObe26v" +
                            "KRZqp6VXevN6Dn6zCnkf" +
                            "695vPChWIYHK83FkAse7" +
                            "60gLp6NsAWUbwOSCiPaP" +
                            "cLD3MFsPaGPaiQ0Nvz8G" +
                            "KshFP5dPFgOwHkMDkoWS" +
                            "QD0PBamidw0slw6eKVTr" +
                            "eChPJyKRhpAPGLZ4FYSa" +
                            "Xicz4dtSUJ3FDaSkvni1" +
                            "DJWYw5NFmro25z94lGIa" +
                            "OLpjpy0BtaJh5TMBiHlq" +
                            "elK6Ar7CiHuhgJTelGNt" +
                            "0eVW1a0wNeo2mZtYqQlx" +
                            "KcwKD7vKMKJPmIzxBlWv" +
                            "XllMVwK2lkOXMZVJYIDV" +
                            "Nb6T0tX1mGbwVVgBWK64" +
                            "5ltSpzaiu9LbwALGgWEF"
                    );

            Navigation.findNavController(view).navigate(R.id.nav_showQrCode,bundle); //takes to generate qr code

        });

        Button btnXML = view.findViewById(R.id.btnCreateXML);
        btnXML.setOnClickListener(v->
        {
            // Navigation.findNavController(view).navigate(R.id.); takes to generate xml
        });

        return view;
    }



}
