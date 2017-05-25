package jne.com.utils;

import java.util.List;

public class WeatherInfo {
    public String status;
    public String msg;
    public ResultW result;

    public WeatherInfo(){
        setStatus("0");
        setStatus("0");
        setResult(null);
    }

    public static class ResultW {
        public String city;
        public String cityid;
        public String citycode;
        public String week;
        public String weather;//天气类型
        public String temp;//实时温度
        public String temphigh;//最高气温
        public String templow;//最低气温
        public String winddirect;//风向
        public String windpower;//风力
        public String updatetime;//更新时间
        public List<Index> index;


        public static class Index {
            public String iname;
            public String ivalue;
            public String detail;

            public String getIname() {
                return iname;
            }

            public String getIvalue() {
                return ivalue;
            }

            public String getDetail() {
                return detail;
            }

        }

        public void setCity(String city) {
            this.city = city;
        }

        public void setCityid(String cityid) {
            this.cityid = cityid;
        }

        public void setCitycode(String citycode) {
            this.citycode = citycode;
        }

        public void setWeek(String week) {
            this.week = week;
        }

        public void setWeather(String weather) {
            this.weather = weather;
        }

        public void setTemp(String temp) {
            this.temp = temp;
        }

        public void setTemphigh(String temphigh) {
            this.temphigh = temphigh;
        }

        public void setTemplow(String templow) {
            this.templow = templow;
        }

        public void setWinddirect(String winddirect) {
            this.winddirect = winddirect;
        }

        public void setWindpower(String windpower) {
            this.windpower = windpower;
        }

        public void setUpdatetime(String updatetime) {
            this.updatetime = updatetime;
        }

        public void setIndex(List<Index> index) {
            this.index = index;
        }

        public String getCity() {
            return city;
        }

        public String getCityid() {
            return cityid;
        }

        public String getCitycode() {
            return citycode;
        }

        public String getWeek() {
            return week;
        }

        public String getWeather() {
            return weather;
        }

        public String getTemp() {
            return temp;
        }

        public String getTemphigh() {
            return temphigh;
        }

        public String getTemplow() {
            return templow;
        }

        public String getWinddirect() {
            return winddirect;
        }

        public String getWindpower() {
            return windpower;
        }

        public String getUpdatetime() {
            return updatetime;
        }

        public List<Index> getIndex() {
            return index;
        }
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setResult(ResultW result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public String getMsg() {
        return msg;
    }

    public ResultW getResult() {
        return result;
    }

}
