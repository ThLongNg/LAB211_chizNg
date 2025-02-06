/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package core;

import java.util.Objects;

/**
 *
 * @author MyPC
 */
public class Mountain {
  final private int code;
  private String mountain;
  private String province;
  private String description;

  public Mountain(int code, String mountain, String province, String description) {
      this.code = code;
      this.mountain = mountain;
      this.province = province;
      this.description = description;
  }

  public Mountain(int code) {
      this.code = code;
  }

  public int getCode() {
      return code;
  }

  public String getMountain() {
      return mountain;
  }

  public String getProvince() {
      return province;
  }

  public String getDescription() {
      return description;
  }

  @Override
  public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      Mountain mountain = (Mountain) obj;
      return this.code == mountain.code;
  }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.code;
        hash = 97 * hash + Objects.hashCode(this.mountain);
        return hash;
    }
//toString thông tin Mountain ra màn hình
  @Override
  public String toString(){
    return code + " " + mountain + " " + province;
  }
  

}//class Mountain

