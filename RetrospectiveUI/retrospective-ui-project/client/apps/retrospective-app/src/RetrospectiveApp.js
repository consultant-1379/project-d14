/**
 * RetrospectiveApp is defined as
 * `<e-retrospective-app>`
 *
 * Imperatively create application
 * @example
 * let app = new RetrospectiveApp();
 *
 * Declaratively create application
 * @example
 * <e-retrospective-app></e-retrospective-app>
 *
 * @extends {App}
 */
 import { definition } from '@eui/component';
 import { App, html } from '@eui/app';
 import style from './retrospectiveApp.css';
 
 import 'retro-component';
 import '@eui/layout';
 
 @definition('e-retrospective-app', {
   style,
   props: {
     response: { attribute: false },
     retros: { attribute: false, type: 'list', default: [] },
     open: { attribute: true, type: Boolean, default: false },
     chosenTeam: { attribute: true, type: String, default: '' }
   },
 })
 export default class RetrospectiveApp extends App {
   // Uncomment this block to add initialization code
   constructor() {
     //location.reload();
     super();
     const t = localStorage.getItem('TeamChosen');
     console.log("T:", t);
     this.chosenTeam = t;
     console.log("X:", this.chosenTeam);
     var url = "http://localhost:9090/retrospective/team/"+this.chosenTeam;
     //this.chosenTeam = localStorage.getItem('TeamChosen');

     

     this.fetchRetros(url);
     // initialize
   }

   didChangeProps(changedProps){
    if (changedProps.has('chosenTeam')) {
      location.reload();
    }
   }

   createRetro(){
    const body = {
        teamId: this.chosenTeam //needs to be changed to current team
    };

    const response = fetch('http://localhost:9090/retrospective', {
      method: 'POST', 
      body: JSON.stringify(body),
      headers: {'Content-Type': 'application/json'}
    });
    location.reload();
  }
 
   fetchRetros(urlParam){
     // fetch will need to be change to relative ports
     fetch(urlParam)
     .then((Response => Response.json()))
       .then((jsonResponse) => {
         let temp = [];
         //var present = new Date();
         jsonResponse.forEach((retro) => {
           temp =[...temp, retro];
         });
         this.retros = [...temp];
           console.log("All retrospectives: ", this.retros); // print out all the teams to the console
           console.log("URL: ", urlParam);
         })
         .catch((error) => {
           console.log('Error fetching retrospectives: check if URL is valid');
         });
        //location.reload();
   }
 
   createRetroComponents = (input) => input.map(retro =>
     html `
       <e-retro-component
        retroid=${retro.id}
        completed=${retro.complete}
        teamid = ${retro.teamId}>
       </e-retro-component>`
   );
 
   checkForRetros(){
     console.log("Retros length",this.retros.length);
     return this.retros.length === 0 ? html `No retrospectives for this team` : this.createRetroComponents(this.retros);
   }

   showDialogButton(){
    return html `
    <eui-base-v0-dialog label="Subscribe">
      ${console.log("About to render dialog box button")}
      <div slot="content">
        Dialog is appearing here
      </div>
      <eui-base-v0-button slot="bottom" primary>Button</eui-base-v0-button>
    </eui-base-v0-dialog>`
   }

   openDialog(){
    this.open = true;
    console.log("Calling open dialog");
   }

   /**
   * Render the <e-retrospective-app> app. This function is called each time a
   * prop changes.
   */
   render() {
     const { EUI } = window;
     return html`
     <eui-layout-v0-multi-panel-tile tile-title="Retrospectives: ${localStorage.getItem('TeamChosenName')}">
       <div slot="content">
         ${this.checkForRetros()}
       </div>
        
       <eui-layout-v0-tile-panel
         id="filter"
         tile-title="Filter"
         subtitle="left filter"
         slot="left"
         icon-name="filter"
         width=400
       >
       
       <div slot="content">
       <!-- Content for "Filter" tile panel goes here -->
       </div>
       </eui-layout-v0-tile-panel>
 
       <eui-layout-v0-tile-panel
         id="settings"
         tile-title="Create retrospective"
         slot="right"
         icon-name="plus"
         width=300
       >
         <div slot="content">
           <!-- Content for "Settings" tile panel goes here -->
           <div class = "row">
             Created retrospectives are automatically assigned a retrospective ID and a team ID corresponding to your current team
           </div>
           <div class = "row">
             <eui-base-v0-button position="right"
                 @click=${(event) => 
                   this.createRetro()}>
                 Create retro
             </eui-base-v0-button>
           </div>
         </div>
       </eui-layout-v0-tile-panel>
     </eui-layout-v0-multi-panel-tile>`;
   }
 }
 
 /**
  * Register the component as e-retrospective-app.
  * Registration can be done at a later time and with a different name
  * Uncomment the below line to register the App if used outside the container
  */
 // RetrospectiveApp.register();
 