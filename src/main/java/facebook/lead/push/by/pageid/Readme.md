### Write data as json file 
    FileWriter fileWriter = new FileWriter(BASE_DIR+"/output.json");
    fileWriter.write(facebookResponseJSON.toString());
    fileWriter.close();
