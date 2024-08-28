/**
 * Integration tests for <e-plan-app>
 */
import { expect } from 'chai';
import PlanApp from '../PlanApp';
import {
  inShadow,
  injectHTMLElement,
} from '../../../../../test/utils';

describe('PlanApp Application Tests', () => {
    let container;
    let inject;
    before(() => {
      container = document.body.appendChild(document.createElement('div'));
      inject = injectHTMLElement.bind(null, container);
      window.EUI = undefined; // stub out the locale
      PlanApp.register();
    });

    after(() => {
      document.body.removeChild(container);
    });

    describe('Basic application setup', () => {
      it('should create a new <e-plan-app>', async () => {
        const appUnderTest = await inject('<e-plan-app></e-plan-app>');
        // check shadow DOM
        const headingTag = inShadow(appUnderTest, 'h1');
        expect(headingTag.textContent, '"Your app markup goes here" was not found').to.equal('Your app markup goes here');
      });
    });
});
