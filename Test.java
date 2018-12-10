
Map<String, String[]> map =
   request.getParameterMap();

for (Map.Entry<String, String[]> entry : map.entrySet()) {
  out.println(" * key / value: " + entry.getKey() + " / " + entry.getValue()[0]);
/*    if (entry.getKey()=="name") {
    String value = entry.getValue()[0];
    out.println(value);
  }*/
}
