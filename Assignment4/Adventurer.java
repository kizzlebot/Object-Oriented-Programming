
public class Adventurer extends Character{
	private String desc ;
	public Adventurer(Cave initLoc) {
		super(initLoc);
		this.desc = new String();
	}

	@Override
	public boolean modifyCave(Cave loc) {
		return false;
	}
	
	public boolean move(Cave to){
		if ((to.isOpen()||to.isPit()||to.isTeleport())&&!to.isOccupied()){
			
			super.move(to);
			//System.out.println("Rows In ok: "+to.getRow()+" Columns: "+to.getCol());
			if (this.location.isTeleport()){
				this.location.setMarked(true);
				desc += "Adventurer marks teleport";
			}
			//this.location.setMarked(true);
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

	@Override
	public String describeModification() {
		System.out.println(desc);
		String temp = this.desc;
		this.desc = "" ; 
		
		return temp;
	}


}
