private void draw() {

	// Erase the old image.
	graphics.setColor(Color.BLACK);
	graphics.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

	// Try and get the depth
	depth = Integer.parseInt(recDepthTextField.getText());
	try {
		//check if depth is in correct format/range else throw NumberFormatException();
		if(depth>0 && depth<11){
		//what do we want it to do?

		}
	} catch (NumberFormatException ex) {

		System.out.println("NullPointer Exception");

		return;
	}

	// Generate the color scheme to use.
	if (randomizeCheckBox.isSelected()) {
		colorScheme = new Color[depth+1];
		for(int i=0; i<colorScheme.length; i++){
			/*i want to use this, but it doesn't work*/ 
			colorScheme[i] = Color.getColor();
			/*This one works, but i have no clue what it does... got it off line*/
			colorScheme[i] = Color.getHSBColor((float)Math.random(), 1.0f, 1.0f);
		}	
	}
	else {
		colorScheme[i] = (COLOR)colorComboBox[i].getSelectedItem.getColor(Color);
	}

	// Draw the base triangle.
	graphics.setColor(Color.WHITE);
	graphics.drawLine(0, SIZE, SIZE, SIZE);
	graphics.drawLine(0, SIZE, (SIZE/2), 0);
	graphics.drawLine((SIZE/2), 0, SIZE, SIZE);



	// Now draw the rest of the inner triangles with the recursive function.
	draw(depth,0,0,SIZE);
}

// Recursive function to draw triangles at a given depth at the specified square given.
private void draw(int d, int x, int y, int S) {
	if (d==0) return;

	// Otherwise, draw big triangle at this level, between the points
	// shown in the figure. You can use the fillPolygon() method of
	// the Graphics object of your Canvas. Make sure you get the color
	// right!


	try{
		paint(getGraphics());
	}catch(NullPointerException ex){
		System.out.println("NullPointer Exception");
	}

	//graphics.setColor(colorScheme[depth-d]%colorScheme.length);

	int[] xVert = {x, x+S/2, x+S};
	int[] yVert = {y+S, y, y+S};

	graphics.fillPolygon(xVert, yVert, 3);




	// Draw the subtriangles. The self-similarity of fractals means
	// that they are themselves Sierpinski triangles of depth d-1.
	// draw top middle triangle
	draw(d-1, x+S/4, y, S/2);

	// draw bottom left triangle
	draw(d-1, x, y+S/2, S/2);

	// draw bottom right triangle
	draw(d-1, x+S/2, y+S/2, S/2);

}