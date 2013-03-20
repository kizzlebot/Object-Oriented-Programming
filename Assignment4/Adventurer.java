
public class Adventurer extends Character{
	
	public Adventurer(Cave initLoc) {
		super(initLoc);
	}

	@Override
	public boolean modifyCave(Cave loc) {
		if (loc.isTeleport()){
			return true;
		}
		else{
			return false;
		}
	}
	
	public boolean move(Cave to){
		if (to.isOpen()||to.isPit()||modifyCave(to)){
			System.out.println("Rows In ok: "+to.getRow()+" Columns: "+to.getCol());
			super.move(to);
			
			this.location.setMarked(true);
			return true;
		}
		
		else {
			return false;
		}
	}
	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "Adventurer";
	}


}
