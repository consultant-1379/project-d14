/**
 * TeamretroApp is defined as
 * `<e-teamretro-app>`
 *
 * Imperatively create application
 * @example
 * let app = new TeamretroApp();
 *
 * Declaratively create application
 * @example
 * <e-teamretro-app></e-teamretro-app>
 *
 * @extends {App}
 */
 import { definition } from '@eui/component';
 import { App, html } from '@eui/app';
 import style from './teamretroApp.css';
 
 import '@eui/draggable';
 import '@eui/layout';
 import { Dialog } from '@eui/base';
 import 'item-component';
 
 @definition('e-teamretro-app', {
   style,
   props: {
     response: { attribute: false },
     items: { attribute: false, type: 'list', default: [] },
     currentRetro: { attribute: true, type: String, default: "6310d4864aad6a3fdb163817"},
     newItemDescription: { attribute: true, type: String, default:'' },
     chosenCategory: { attribute: true, type: String, default: '' },
     isLocked: { attribute: true, type: Boolean, default: false}
   },
 })
 export default class TeamretroApp extends App {
   // Uncomment this block to add initialization code
   constructor() {
     super();
     this.fetchItems();
     // initialize
   }
 
   /**
   * Render the <e-teamretro-app> app. This function is called each time a
   * prop changes.
   */
    didChangeProps(changedProps) {
      if (changedProps.has('chosenCategory')) {
        // propA has changed, do some custom stuff here…
        console.log("Chosen category:", this.chosenCategory);
      }
    }
    

   createItem(itemDescription){
    const body = {
      retrospectiveId: "6310bf3409788252b006acc4",
      description: itemDescription,
      category: this.chosenCategory
    };

    const response = fetch("http://localhost:9090/retrospective/item/6310bf3409788252b006acc4", {
      method: 'POST', 
      body: JSON.stringify(body),
      headers: {'Content-Type': 'application/json'}
    });
    location.reload();
   }
 
   fetchItems(){
     fetch('http://localhost:9090/retrospective/item/6310bf3409788252b006acc4') // this url will fetch the items for the individual retrospectives
     .then((Response => Response.json()))
       .then((jsonResponse) => {
         let temp = [];
         //var present = new Date();
         jsonResponse.forEach((item) => {
           temp =[...temp, item];
         });
         this.items = [...temp];
           console.log("All items: ", this.items); // print out all the teams to the console
         })
         .catch((error) => {
           console.log('Error fetching items');
         });
   }
 
   // used to generate item components that can be dragged around the plan
   generateItemComponents = (input, filter) => input
   .filter(item => item.category === filter)
   .map(item =>
     html `
     <e-item-component
       item-id=${item.id}
       item-description=${item.description}
       item-category=${item.category}>
     </e-item-component>`
   );
 
   render() {
     const { EUI } = window;
     return html`
     
     <eui-layout-v0-multi-panel-tile tile-title="Manage retrospective items">
      
       <div slot = "content">
         <div class="row">
         <eui-base-v0-button onclick="
            const notification = document.createElement('eui-base-v0-notification');
            notification.description = 'Items can no longer be modified in this retrospective';
            notification.textContent = 'Plan board locked.';
            notification.timeout = 5000;

            const notificationDescription = document.createElement('div');
            notificationDescription.setAttribute('slot', 'description');
            notificationDescription.innerHTML = 'Simple description (added via the description slot)';

            //notification.appendChild(notificationDescription);

            notification.addEventListener('click', event => {
              console.log('clicked');
            });
            notification.showNotification();" slot="example">
          <eui-v0-icon name="lock-locked"></eui-v0-icon> Lock plan board</eui-base-v0-button>
        <eui-base-v0-accordion category-title="Create new item">
       <div class="row">
          <eui-base-v0-textarea placeholder="Describe your new item..." @change=${(event) => 
          this.newItemDescription = event.detail.value
            }>
            ${console.log("New item description:", this.newItemDescription)}
            </eui-base-v0-textarea>
            <eui-base-v0-dropdown label="Category" data-type="single">
            <eui-base-v0-menu-item value="value-1" label="GLAD" @click=${(event) => this.chosenCategory = "GLAD"}></eui-base-v0-menu-item>
            <eui-base-v0-menu-item value="value-2" label="MAD" @click=${(event) => this.chosenCategory = "MAD"}></eui-base-v0-menu-item>
            <eui-base-v0-menu-item value="value-2" label="SAD" @click=${(event) => this.chosenCategory = "SAD"}></eui-base-v0-menu-item>
          </eui-base-v0-dropdown>
         </div>
         <div class="row">
           <eui-base-v0-button @click=${(event) => 
            this.createItem(this.newItemDescription)}>Add item
           </eui-base-v0-button>
         </div>
       </eui-base-v0-accordion>
       </div>
     <div class = "row">
     <eui-layout-v0-tabs>
     <eui-layout-v0-tab selected><eui-v0-icon name="weather-sun"></eui-v0-icon>Glad</eui-layout-v0-tab>
      <eui-layout-v0-tab><eui-v0-icon name="weather-storm"></eui-v0-icon>Mad</eui-layout-v0-tab>
      <eui-layout-v0-tab><eui-v0-icon name="cloud"></eui-v0-icon>Sad</eui-layout-v0-tab>
    
      <div slot = "content" selected>
        ${this.generateItemComponents(this.items, "GLAD")}
      </div>

      <div slot="content">
        ${this.generateItemComponents(this.items, "MAD")}
      </div>

      <div slot="content">
        ${this.generateItemComponents(this.items, "SAD")}
      </div>

      
    </eui-layout-v0-tabs>
       </div>
     </div>
     </div>
     </eui-layout-v0-multi-panel-tile>
     `;
   }
 }
 
 /**
  * Register the component as e-teamretro-app.
  * Registration can be done at a later time and with a different name
  * Uncomment the below line to register the App if used outside the container
  */
 // TeamretroApp.register();
 