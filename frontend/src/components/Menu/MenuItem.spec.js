import MenuItem from './MenuItem';
import { shallowToJson } from 'enzyme-to-json';
import { shallow } from 'enzyme';

describe('MenuItem component', () => {

    it("should render the component", () => {
        const wrapper = shallow(<MenuItem />);
        expect(shallowToJson(wrapper)).toMatchSnapshot();
    });
});

