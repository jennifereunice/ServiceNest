import React, { useEffect, useState } from "react";
import { getVendorProfile, updateVendorProfile } from "../../api/VendorProfileService";

const VendorProfile = () => {
  const [profile, setProfile] = useState({});
  const [loading, setLoading] = useState(true);
  const [saving, setSaving] = useState(false);

  const loadProfile = async () => {
    try {
      setLoading(true);
      const res = await getVendorProfile();
      setProfile(res.data);
    } catch (err) {
      console.error(err);
      alert("Failed to load profile");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadProfile();
  }, []);

  const saveProfile = async () => {
    try {
      setSaving(true);
      const res = await updateVendorProfile(profile);
      setProfile(res.data);
      alert("Profile updated successfully");
    } catch (err) {
      console.error(err);
      alert("Failed to update profile");
    } finally {
      setSaving(false);
    }
  };

  if (loading) return <p className="text-center mt-10">Loading...</p>;

  return (
    <div className="max-w-3xl mx-auto mt-10 p-6 bg-white shadow-md rounded-lg">
      <h2 className="text-2xl font-bold mb-6 text-gray-800">My Vendor Profile</h2>

      {/* Business Name */}
      <div className="mb-4">
        <label className="block text-gray-700 mb-1">Business Name</label>
        <input
          type="text"
          value={profile.businessName || ""}
          onChange={e => setProfile({ ...profile, businessName: e.target.value })}
          className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
          placeholder="Your business name"
        />
      </div>

      {/* Address */}
      <div className="mb-4">
        <label className="block text-gray-700 mb-1">Address</label>
        <input
          type="text"
          value={profile.address || ""}
          onChange={e => setProfile({ ...profile, address: e.target.value })}
          className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
          placeholder="Your address"
        />
      </div>

      {/* City */}
      <div className="mb-4">
        <label className="block text-gray-700 mb-1">City</label>
        <input
          type="text"
          value={profile.city || ""}
          onChange={e => setProfile({ ...profile, city: e.target.value })}
          className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
          placeholder="City"
        />
      </div>

      {/* Experience Years */}
      <div className="mb-4">
        <label className="block text-gray-700 mb-1">Experience (years)</label>
        <input
          type="number"
          value={profile.experienceYears || ""}
          onChange={e => setProfile({ ...profile, experienceYears: parseInt(e.target.value) })}
          className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
          placeholder="Number of years"
        />
      </div>

      {/* Service Type */}
      <div className="mb-4">
        <label className="block text-gray-700 mb-1">Service Type</label>
        <input
          type="text"
          value={profile.serviceType || ""}
          onChange={e => setProfile({ ...profile, serviceType: e.target.value })}
          className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
          placeholder="e.g., Plumber, Electrician"
        />
      </div>

      {/* Govt ID */}
      <div className="mb-6">
        <label className="block text-gray-700 mb-1">Government ID Number</label>
        <input
          type="text"
          value={profile.govtIdNumber || ""}
          onChange={e => setProfile({ ...profile, govtIdNumber: e.target.value })}
          className="w-full border border-gray-300 rounded px-3 py-2 focus:outline-none focus:ring-2 focus:ring-blue-500"
          placeholder="Your ID number"
        />
      </div>

      <button
        onClick={saveProfile}
        disabled={saving}
        className="bg-blue-600 text-white px-6 py-2 rounded hover:bg-blue-700 focus:outline-none focus:ring-2 focus:ring-blue-500"
      >
        {saving ? "Saving..." : "Save Changes"}
      </button>
    </div>
  );
};

export default VendorProfile;
