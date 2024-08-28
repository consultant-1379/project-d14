/**
 * PlanApp is defined as
 * `<e-plan-app>`
 *
 * Imperatively create application
 * @example
 * let app = new PlanApp();
 *
 * Declaratively create application
 * @example
 * <e-plan-app></e-plan-app>
 *
 * @extends {App}
 */
import '@eui/base';
import { definition } from '@eui/component';
import { App, html } from '@eui/app';
import style from './planApp.css';

import 'progress-visualizer';
import '@eui/layout';
import '@eui/draggable';

import 'item-component';
import 'task-component';

//import

@definition('e-plan-app', {
  style,
  props: {
    toDo: { attribute: false, type: 'list', default: []},
    inProgress: { attribute: false, type: Array, default: []},
    done: { attribute: false, type: 'list', default: [] },
    toDoCounter: { attribute: true, type: Number, default: 0},
    doneCounter: { attribute: true, type: Number, default: 0},
    totalTasks: { attribute: true, type: Number, default: 0}
  },
})
export default class PlanApp extends App {
  // Uncomment this block to add initialization code
  constructor() {
    super();
    this.fetchItems();
  }

  /**
  * Render the <e-plan-app> app. This function is called each time a
  * prop changes.
  */
   fetchItems(){
    fetch('http://localhost:9090/retrospective/item/6310bf3409788252b006acc4') // this url will fetch the items for the individual retrospectives
    .then((Response => Response.json()))
      .then((jsonResponse) => {
        let temp = [];
        //var present = new Date();
        jsonResponse.forEach((item) => {
          temp =[...temp, item];
        });
        this.toDo = [...temp];
          console.log("All items: ", this.toDo.length); // print out all the teams to the console
        })
        .catch((error) => {
          console.log('Error fetching items');
        });
  }

  generateItemComponents = (input) => input.map(item =>
    html `
    <e-task-component
      description=${item.description}>
    </e-task-component>`
  );

  doTask(){
    this.done = [...this.done, 'Done task'];
  }

  undoTask(){
    //let arr = this.done
    this.done.pop();
  }

  handleEvent(event) {
    // handle the addition of an item to the drop area
    if (event.type === 'add') {
      console.log(`Card Title: ${event.item.cardTitle}`);
      // Card Title: Card One
  
      console.log(`Title of destination drop area: ${event.to.dropAreaTitle}`);
      // Title of destination drop area: Drop Area Two
  
      console.log(`Title of originating drop area: ${event.from.dropAreaTitle}`);
      // Title of originating drop area: Drop Area One

      if(event.to.dropAreaTitle === "Done" && (event.from.dropAreaTitle === "To Do" || event.from.dropAreaTitle === "In Progress")){
        ++this.doneCounter;
      }
      if((event.to.dropAreaTitle === "To Do" || event.to.dropAreaTitle === "In Progress") && event.from.dropAreaTitle === "Done"){
        --this.doneCounter;
      }
      console.log("Done counter:",this.doneCounter);
    }
  }

  render() {
    return html`
    ${console.log(this.toDoCounter)}
    <eui-layout-v0-multi-panel-tile tile-title="Manage sprint tasks">
      <div slot="content">
        <div class ="row">
          <div class="column">
        <e-progress-visualizer 
        total=${this.toDo.length} 
        remaining=${
          this.toDo.length + this.inProgress.length - this.doneCounter
          }></e-progress-visualizer>
          </div>
        </div>
        <!-- <eui-draggable-v0-drop-area border drop-area-title="To Do"
        @add=${this}>
          <eui-layout-v0-card card-title="Card One" drag></eui-layout-v0-card>
        </eui-draggable-v0-drop-area> -->
        <!-- <eui-draggable-v0-drop-area border drop-area-title="Done"
          @add=${this}>
      </eui-draggable-v0-drop-area> -->
      <div class = "row">
       <div class ="column">
         <eui-draggable-v0-drop-area
           border drop-area-title="To Do" @add=${this}>
           ${this.generateItemComponents(this.toDo)}
         </eui-draggable-v0-drop-area>
       </div>
       <div class="column">
       <eui-draggable-v0-drop-area 
           border @add=${this}
           drop-area-title="In Progress"
           >
         </eui-draggable-v0-drop-area>
       </div>
       <div class="column">
       <eui-draggable-v0-drop-area
           border @add=${this}
           drop-area-title="Done"
           >
         </eui-draggable-v0-drop-area>
       </div>
     </div>
      </eui-layout-v0-multi-panel-tile>
    `;
  }
}

/**
 * Register the component as e-plan-app.
 * Registration can be done at a later time and with a different name
 * Uncomment the below line to register the App if used outside the container
 */
// PlanApp.register();
