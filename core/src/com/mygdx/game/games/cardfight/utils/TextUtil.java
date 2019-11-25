package com.mygdx.game.games.cardfight.utils;

public class TextUtil {
  public enum SymbolSubstitutions {
    DAMAGE("#D#"),
    MAGIC_NUMBER("#M#");
    SymbolSubstitutions(String symbol) {
      this.symbol = symbol;
    }

    public String substitute(String originalString, String replacement) {
      return originalString.replaceAll(this.symbol, replacement);
    }

    public final String symbol;
  }
}
